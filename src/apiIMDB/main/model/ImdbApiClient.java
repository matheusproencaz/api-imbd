package apiIMDB.main.model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import build.API_KEY;

public class ImdbApiClient {
	
	private String apiKey = API_KEY.apiKey;
	private String URI;
	
	public ImdbApiClient(String URI){
		this.URI = URI;
	}
	
	public String httpRequest() {
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