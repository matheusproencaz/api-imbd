package apiIMDB.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import apiIMDB.main.interfaces.Content;
import apiIMDB.main.interfaces.JsonParser;
import apiIMDB.main.model.MarvelSeries;

public class MarvelJsonParser implements JsonParser{
	
	private String json;
	
	public MarvelJsonParser(String json) {
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
	
	private List<String> dataNumberToList(String chave) {
		List<String> list = new ArrayList<>();

		Matcher mChave = Pattern.compile("\"" + chave + "\":\\s*(.*?)\\s*[,}]").matcher(json);
		while (mChave.find()) {
			list.add(mChave.group(1));
		}

		return list;
	}
	
	private List<String> dataImageToList() {
		List<String> list = new ArrayList<>();

		Matcher mChave = Pattern.compile("\"thumbnail\":\\{\"path\":\\s*[\"](.*?)[\"]\\s*[,]").matcher(json);
		while (mChave.find()) {
			list.add(mChave.group(1)+".jpg");
		}

		return list;
	}
	
	public List<? extends Content> parse() {
		List<MarvelSeries> dadosSeries = new ArrayList<>();
		List<String> titles = dataToList("title");
		List<String> year = dataNumberToList("startYear");
		List<String> image = dataImageToList();
		List<String> rating = dataToList("rating");
		
		for (int i = 0; i < titles.size(); i++) {
			MarvelSeries marvelSeries = new MarvelSeries(titles.get(i), year.get(i),
					image.get(i), rating.get(i));
			
			dadosSeries.add(marvelSeries);
		}
		
		return dadosSeries;
	}
}