package com.anurag.CoronaVirusTracker.service;


import com.anurag.CoronaVirusTracker.model.CoronaModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    private static String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";


    private List<CoronaModel> coronaStats = new ArrayList<>();

    public List<CoronaModel> getCoronaStats() {
        return coronaStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchCoronaData() throws IOException, InterruptedException {
        List<CoronaModel> newcoronaStats= new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());
        StringReader stringReader = new StringReader(httpResponse.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);
        for (CSVRecord record : records) {
            CoronaModel coronaModel = new CoronaModel();

            coronaModel.setState(record.get("Province/State"));
            coronaModel.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size()-1));
            int previousCases = Integer.parseInt(record.get(record.size()-2));
            int newCases = latestCases-previousCases;

            coronaModel.setConfirmedCase(latestCases);
            coronaModel.setNewCases(newCases);

            //System.out.println(coronaModel.toString());
            newcoronaStats.add(coronaModel);
        }

         this.coronaStats=newcoronaStats;



    }

}
