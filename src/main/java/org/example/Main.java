package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.SimpleTimeZone;
import java.util.SplittableRandom;
//
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        String keyw = "NCcTGCP3kVqYzz8o5CaAFAtNO6Zgbm19p7OytQDF";
        String url = "https://api.nasa.gov/planetary/apod?api_key="+keyw;

        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpResponse response = client.execute(new HttpGet(url));
        //Scanner scanner = new Scanner(response.getEntity().getContent());
      // System.out.println(scanner.nextLine());
        Nasa answer = mapper.readValue(response.getEntity().getContent(),Nasa.class);
        String urlImage = answer.url;
        String[] sepa = urlImage.split("/");
        String fileName = sepa[sepa.length-1];
        CloseableHttpResponse image = client.execute(new HttpGet(answer.url));
        FileOutputStream fos = new FileOutputStream(fileName);
        image.getEntity().writeTo(fos);

    }
}