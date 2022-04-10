package apiIMDB.main.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import apiIMDB.main.interfaces.ApiClient;

public class MarvelApiClient implements ApiClient{
	
	private String apiPublicKey;
	private String apiPrivateKey;
	private String URI = "https://gateway.marvel.com:443/v1/public/series?ts=1&apikey=";

	public MarvelApiClient(String apiPublicKey, String apiPrivateKey){
		this.apiPublicKey = apiPublicKey;
		this.apiPrivateKey = apiPrivateKey;
	}
	
	public String getBody() {
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URI + apiPublicKey + "&hash=" + apiPrivateKey))
					.header("Content-Type", "application/json").GET().build();

			HttpClient httpClient = HttpClient.newBuilder().build();

			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

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