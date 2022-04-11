package apiIMDB.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import apiIMDB.main.interfaces.Content;
import apiIMDB.main.interfaces.JsonParser;
import apiIMDB.main.model.Series;

public class ImdbSeriesJsonParser implements JsonParser{
	
	private String json;
	
	public ImdbSeriesJsonParser(String json) {
		this.json = json;
	}
	
	private List<String> dataToList(String key) {
		List<String> list = new ArrayList<>();

		Matcher mChave = Pattern.compile("\"" + key + "\":\\s*[\"](.*?)[\"]\\s*[,}]").matcher(json);
		while (mChave.find()) {
			list.add(mChave.group(1));
		}
		return list;
	}
	
	public List<? extends Content> parse() {
		List<Series> dadosSeries = new ArrayList<>();
		List<String> ranks = dataToList("rank");
		List<String> titles = dataToList("title");
		List<String> year = dataToList("year");
		List<String> image = dataToList("image");
		List<String> imDbRating = dataToList("imDbRating");
		
		for (int i = 0; i < ranks.size(); i++) {
			Series serie = new Series(Integer.parseInt(ranks.get(i)), titles.get(i), year.get(i),
					image.get(i), imDbRating.get(i));
			dadosSeries.add(serie);
		}
		return dadosSeries;
	}
}