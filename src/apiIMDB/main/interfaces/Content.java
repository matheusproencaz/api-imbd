package apiIMDB.main.interfaces;

public interface Content extends Comparable<Content>{
	String titles();
	String years();
	String urlImage();
	String rating();
	String type();
}