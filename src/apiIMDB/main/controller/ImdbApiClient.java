package apiIMDB.main.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import apiIMDB.main.interfaces.ApiClient;

public class ImdbApiClient implements ApiClient{
	
	private String apiKey;
	private String URI = "https://imdb-api.com/en/API/Top250Movies/";
	
	public ImdbApiClient(String apiKey){
		this.apiKey = apiKey;
	}
	
	public String getBody() {
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URI + apiKey))
					.header("Content-Type", "application/json").GET().build();

			HttpClient httpClient = HttpClient.newBuilder().build();

			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

			System.out.println(response.body());
			return response.body();

		} catch (URISyntaxException e) {
			return "Erro" + e.getMessage();
		} catch (IOException e) {
			return "Erro" + e.getMessage();
		} catch (InterruptedException e) {
			return "Erro" + e.getMessage();
		}
	}
}