package apiIMDB.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import apiIMDB.main.model.ImdbApiClient;
import apiIMDB.main.model.ImdbMovieJsonParser;
import apiIMDB.main.view.HTMLGenerator;

public class ApiIMDB {

	public static void main(String[] args) {
		
		try {
			File file = new File("testHTML.html");
			PrintWriter writer = new PrintWriter(file);
			
			String response = new ImdbApiClient("https://imdb-api.com/en/API/Top250Movies/").httpRequest();
			List<Filme> dadosFilmes = new ImdbMovieJsonParser(response).parse();

			HTMLGenerator htmlGen = new HTMLGenerator(writer);
			htmlGen.generateHTML(dadosFilmes);
			htmlGen.openHTMLFile(file);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void teste(String str) {
		try{
			PrintWriter writer = new PrintWriter(new File("out.txt"));
			writer.write(str);
			writer.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static record Filme(Integer ranks, String titles, Integer years, String image, Double imDbRating) {

		@Override
		public String toString() {
			if (ranks <= 9) {
				return "[0" + ranks + "] | Título:" + titles + "| Ano de Lançamento:" + years + " | image=" + image
						+ " | imDbRating=" + imDbRating + "]";
			} else {
				return "[" + ranks + "] | Título:" + titles + "| Ano de Lançamento:" + years + " | image=" + image
						+ " | imDbRating=" + imDbRating + "]";
			}
		}
	}
}