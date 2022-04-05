package apiIMDB.main;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import build.API_KEY;

public class ApiIMDB {

	public static void main(String[] args) {
			
		String apiKey = API_KEY.apiKey;
		
		try {
			HttpRequest request = HttpRequest
					.newBuilder()
					.uri(new URI("https://imdb-api.com/en/API/SearchMovie/"+apiKey+"/inception"))
					.header("Content-Type", "application/json")
					.GET()
					.build();
			
			HttpClient httpClient = HttpClient.newBuilder().build();
			HttpResponse<String> respose = httpClient.send(request, BodyHandlers.ofString());
			
			System.out.println(respose.body());
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}