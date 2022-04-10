package apiIMDB.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import apiIMDB.main.interfaces.Content;
import apiIMDB.main.interfaces.JsonParser;
import apiIMDB.main.model.Filme;

public class ImdbMovieJsonParser implements JsonParser{
	
	private String json;
	
	public ImdbMovieJsonParser(String json) {
		this.json = json;
	}
	
	private List<String> dataToList(String chave) {
		List<String> list = new ArrayList<>();

		Matcher mChave = Pattern.compile("\"" + chave + "\":\\s*[\"](.*?)[\"]\\s*[,}]").matcher(json);
		while (mChave.find()) {
			list.add(mChave.group(1));
		}
		
		return list;
	}
	
	public List<? extends Content> parse() {
		List<Filme> dadosFilmes = new ArrayList<>();
		List<String> ranks = dataToList("rank");
		List<String> titles = dataToList("title");
		List<String> year = dataToList("year");
		List<String> image = dataToList("image");
		List<String> imDbRating = dataToList("imDbRating");
		
		for (int i = 0; i < ranks.size(); i++) {
			Filme filme = new Filme(Integer.parseInt(ranks.get(i)), titles.get(i), year.get(i),
					image.get(i), imDbRating.get(i));
			dadosFilmes.add(filme);
		}
		return dadosFilmes;
	}
}