package apiIMDB.main.model;

public class Filme {

	private Integer rank;
	private String title;
	private Integer year;
	private String image;
	private Double imDbRating;

	public Filme() {
	}

	public Filme(Integer rank, String title, Integer year, String image, Double imDbRating) {
		this.rank = rank;
		this.title = title;
		this.year = year;
		this.image = image;
		this.imDbRating = imDbRating;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getImDbRating() {
		return imDbRating;
	}

	public void setImDbRating(Double imDbRating) {
		this.imDbRating = imDbRating;
	}

	@Override
	public String toString() {
		return "["+rank + ", title=" + title + ", year=" + year + ", image=" + image + ", imDbRating="
				+ imDbRating+"]";
	}
	
	
}