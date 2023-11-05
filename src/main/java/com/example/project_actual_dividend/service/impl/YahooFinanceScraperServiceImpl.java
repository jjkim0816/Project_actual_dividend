package com.example.project_actual_dividend.service.impl;

import com.example.project_actual_dividend.dto.CompanyDto;
import com.example.project_actual_dividend.dto.DividendDto;
import com.example.project_actual_dividend.dto.ScrapedResult;
import com.example.project_actual_dividend.service.ScraperService;
import com.example.project_actual_dividend.type.Month;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class YahooFinanceScraperServiceImpl implements ScraperService {
    private static final String STATISTICS_URL = "https://finance.yahoo.com/quote/%s/history?period1=%d&period2=%d&interval=1mo";
    private static final String SUMMARY_URL = "https://finance.yahoo.com/quote/%s?p=%s";
    private static final long START_TIME = 86400;

    @Override
    public CompanyDto scrapCompanyByTicker(String ticker) {
        String url = String.format(SUMMARY_URL, ticker, ticker);

        try {
            Document document = Jsoup.connect(url).get();
            Element titleEle = document.getElementsByTag("h1").get(0);
            String title = titleEle.text().split("\\(")[0].trim();

            return CompanyDto.builder()
                .ticker(ticker)
                .name(title)
                .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ScrapedResult scrap(CompanyDto companyDto) {
        ScrapedResult scrapedResult = new ScrapedResult();
        scrapedResult.setCompany(companyDto);
        long endTime = System.currentTimeMillis() / 1000;

        try {
            String url = String.format(STATISTICS_URL, companyDto.getTicker()
                , START_TIME, endTime);
            Connection conn = Jsoup.connect(url);
            Document document = conn.get();

            Elements parsingDivs = document.getElementsByAttributeValue(
                "data-test", "historical-prices"
            );
            Element tableEle = parsingDivs.get(0);

            Element tbody = tableEle.children().get(1);

            List<DividendDto> dividends = new ArrayList<>();
            for (var e : tbody.children()) {
                String txt = e.text();
                if (!txt.endsWith("Dividend")) {
                    continue;
                }

                String[] splits = txt.split(" ");
                int month = Month.strToNumber(splits[0]);
                int day = Integer.parseInt(splits[1].replace(",", ""));
                int year = Integer.parseInt(splits[2]);
                String dividend = splits[3];

                if (month < 0) {
                    throw new RuntimeException(
                        "Unexpected Month enum value -> " + splits[0]
                    );
                }

                dividends.add(DividendDto.builder()
                        .date(LocalDateTime.of(year, month, day, 0, 0))
                        .dividend(dividend)
                        .build());
            }

            scrapedResult.setDividends(dividends);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return scrapedResult;
    }
}
