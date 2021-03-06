package apiIMDB.main.model;

import apiIMDB.main.interfaces.Content;

public record MarvelSeries(String titles, String years, String urlImage, String rating) implements Content{

	@Override
	public String type() {
		return "Series";
	}

	@Override
	public int compareTo(Content o) {
		return this.rating().compareTo(o.rating());
	}
}
