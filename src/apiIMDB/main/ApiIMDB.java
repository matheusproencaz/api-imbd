package apiIMDB.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import apiIMDB.main.controller.ImdbApiClient;
import apiIMDB.main.controller.ImdbMovieJsonParser;
import apiIMDB.main.interfaces.Content;
import apiIMDB.main.view.HTMLGenerator;
import build.API_KEY;

public class ApiIMDB {

	public static void main(String[] args) {
		try {
			File file = new File("TOP250IMBDMovies.html");
			PrintWriter writer = new PrintWriter(file);
			
			String apiKey = API_KEY.apiKey;
			String response = new ImdbApiClient(apiKey).getBody();
			List<? extends Content> dadosFilmes = new ImdbMovieJsonParser(response).parse();
						
//			String apiPublicKeyMarvel = API_KEY.apiPublicKeyMarvel;
//			String apiPrivateKeyMarvel = API_KEY.apiPrivateKeyMarvel;
//			String response = new MarvelApiClient(apiPublicKeyMarvel, apiPrivateKeyMarvel).getBody();
//			List<? extends Content> dadosFilmes = new MarvelJsonParser(response).parse();
			
			HTMLGenerator htmlGen = new HTMLGenerator(writer);
			
			
			htmlGen.generateHTML(dadosFilmes, "Top 250 IMDB Movies");
			htmlGen.openHTMLFile(file);
			writer.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}