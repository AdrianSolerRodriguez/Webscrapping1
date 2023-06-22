package webscapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1 {
    public static void main(String[] args) {
        String searchQuery = "juegos";
        String fileName = "amazon_products.csv";

        try {
            
            String url = "https://www.amazon.com/s?k=" + searchQuery;
            Document doc = Jsoup.connect(url).get();
         
            Elements productElements = doc.select("div[data-component-type='s-search-result']");
  
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            writer.write("Titulo, Precio (EUR)");
            writer.newLine();

            for (Element productElement : productElements) {
                String title = productElement.select("h2 a span").text();
                String price = productElement.select("span.a-offscreen").text();

                writer.write(title + ", " + price);
                writer.newLine();
            }

            writer.close();

            System.out.println("El web scraping se ha completado correctamente. Los datos se han guardado en " + fileName);
        } catch (IOException e) {
            System.out.println("Ocurrió un error durante el web scraping: " + e.getMessage());
        }
    }
}
