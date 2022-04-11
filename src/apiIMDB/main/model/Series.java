package apiIMDB.main.model;

import apiIMDB.main.interfaces.Content;

public record Series(Integer ranks, String titles, String years, String urlImage, String rating) implements Comparable <Content>, Content{

	@Override
	public String type() {
		return "Series";
	}

	@Override
	public String toString() {
		if (ranks <= 9) {
			return "[0" + ranks + "] | T�tulo:" + titles + "| Ano de Lan�amento:" + years + " | image=" + urlImage
					+ " | imDbRating=" + rating + "]";
		} else {
			return "[" + ranks + "] | T�tulo:" + titles + "| Ano de Lan�amento:" + years + " | image=" + urlImage
					+ " | imDbRating=" + rating + "]";
		}
	}

	@Override
	public int compareTo(Content o) {
		return this.rating().compareTo(o.rating());
	}
}
