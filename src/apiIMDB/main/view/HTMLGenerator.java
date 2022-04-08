package apiIMDB.main.view;

import java.util.List;

import apiIMDB.main.ApiIMDB.Filme;

public class HTMLGenerator {

	public String generateDIVs(List<Filme> filmes) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < filmes.size(); i++) {
			sb.append(generateDIV(
					filmes.get(i).titles(), 
					filmes.get(i).years(), 
					filmes.get(i).image(), 
					filmes.get(i).imDbRating()));
		}
		return sb.toString();
	}
	
	public String generateHTML(List<Filme> filmes) {
		String html = 
				"""
				<!DOCTYPE html>
				<html lang="pt-BR">
				<head>
					<meta charset=\"utf-8\">
					<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
					<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" 
					+ "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">
					<title>Top 250 Filmes IMDB</title>
				</head>
				<body>
					<div class="row align-items-start">
						"""
						+generateDIVs(filmes)+
						"""
					</div>
				</body>
				</html>
				""";
		return html;
	}
	
	public String generateDIV(String title, Integer years, String image, Double imDbRating) {
		String divTemplate =
				"""
				<div class="col-sm-2">
					<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
						<h4 class=\"card-header\">%s</h4>
						<div class=\"card-body\">
							<img class=\"card-img\" src=\"%s\" alt=\"%s\">
							<p class=\"card-text mt-2\">Nota: %,.1f - Ano: %d</p>
						</div>
					</div>
				</div>
				""";
		return String.format(divTemplate, title, image, title, imDbRating, years);
	}
}