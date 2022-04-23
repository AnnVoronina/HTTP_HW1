import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


public class Main {

    static final String  URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();
  //  public static List<Cat> catList = null;
    public static void main(String[] args) throws IOException {

        //Метод createDefault () класса HttpClients возвращает объект CloseableHttpClient ,
        // который является базовой реализацией интерфейса HttpClient
        // CloseableHttpClient httpclient = HttpClients.createDefault();

        // Создаем сами
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
//Класс HttpGet представляет запрос HTTPGET, который получает информацию о заданном сервере, используя URI.
        HttpGet httpget = new HttpGet(URI);
    //    httpget.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType()); //получение и вывод заголовков

//Метод execute () класса CloseableHttpClient принимает объект HttpUriRequest (interface)
// (т. Е. HttpGet, HttpPost, HttpPut, HttpHead и т. Д.)
        CloseableHttpResponse httpresponse = httpClient.execute(httpget);
        Arrays.stream(httpresponse.getAllHeaders()).forEach(System.out::println);

     //   if (httpresponse.getEntity().getContentLength() > 0) // проверяем что сервер возвращает не нулевой ответ

         List<Cat> catList = mapper.readValue //Метод десериализации содержимого JSON из данного файла в заданный тип Java
                    (httpresponse.getEntity().
                            getContent(), new TypeReference<List<Cat>>() {});

         catList.stream().filter(cat -> cat.getUpvotes()!= null).forEach(System.out::println);



        httpClient.close();


    }


}
