package apiIMDB.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import apiIMDB.main.controller.ImdbApiClient;
import apiIMDB.main.controller.ImdbMovieJsonParser;
import apiIMDB.main.controller.ImdbSeriesJsonParser;
import apiIMDB.main.interfaces.Content;
import apiIMDB.main.view.HTMLGenerator;
import build.API_KEY;

public class ApiIMDB {

	public static void main(String[] args) {
		try {
			File file = new File("TOP250IMBDMovies.html");
			PrintWriter writer = new PrintWriter(file);
			
			String apiKey = API_KEY.apiKey;
			String responseMovies = new ImdbApiClient(apiKey, "movies").getBody();
			List<? extends Content> dadosFilmes = new ImdbMovieJsonParser(responseMovies).parse();

			String responseSeries = new ImdbApiClient(apiKey, "series").getBody();
			List<? extends Content> dadosSeries = new ImdbSeriesJsonParser(responseSeries).parse();
			
			List<? extends Content> mixedList = Stream.of(dadosFilmes, dadosSeries)
							.flatMap(Collection::stream)
							.collect(Collectors.toList());
			
			Collections.sort(mixedList, Comparator.comparing(Content::years).reversed());
			
//			String apiPublicKeyMarvel = API_KEY.apiPublicKeyMarvel;
//			String apiPrivateKeyMarvel = API_KEY.apiPrivateKeyMarvel;
//			String response = new MarvelApiClient(apiPublicKeyMarvel, apiPrivateKeyMarvel).getBody();
//			List<? extends Content> dadosFilmes = new MarvelJsonParser(response).parse();
			
			HTMLGenerator htmlGen = new HTMLGenerator(writer);
			
			htmlGen.generateHTML(mixedList, "IMDB Movies and Series");
			htmlGen.openHTMLFile(file);
			writer.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}