package apiIMDB.main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import apiIMDB.main.ApiIMDB.Filme;

public class ImdbMovieJsonParser {
	
	private String json;
	//String json = new ImdbApiClient("https://imdb-api.com/en/API/Top250Movies/").httpRequest();
	
	public ImdbMovieJsonParser(String json) {
		this.json = json;
	}
	
	private List<String> listarDados(String chave) {
		List<String> list = new ArrayList<>();

		Matcher mChave = Pattern.compile("\"" + chave + "\":\\s*[\"](.*?)[\"]\\s*[,}]").matcher(json);
		while (mChave.find()) {
			list.add(mChave.group(1));
		}
		
		return list;
	}
	
	public List<Filme> parse() {
		List<Filme> dadosFilmes = new ArrayList<>();
		List<String> ranks = listarDados("rank");
		List<String> titles = listarDados("title");
		List<String> year = listarDados("year");
		List<String> image = listarDados("image");
		List<String> imDbRating = listarDados("imDbRating");
		
		for (int i = 0; i < ranks.size(); i++) {
			Filme filme = new Filme(Integer.parseInt(ranks.get(i)), titles.get(i), Integer.parseInt(year.get(i)),
					image.get(i), Double.parseDouble(imDbRating.get(i)));
			dadosFilmes.add(filme);
		}
		return dadosFilmes;
	}
	
}
