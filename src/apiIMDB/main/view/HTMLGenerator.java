package apiIMDB.main.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import apiIMDB.main.interfaces.Content;

public class HTMLGenerator {

	private PrintWriter writer;
	
	public HTMLGenerator(PrintWriter writer){
		this.writer = writer;
	}
	
	public void generateHTML(List<? extends Content> content, String titleHTML) {
		String html = 
				"""
				<!DOCTYPE html>
				<html lang="pt-BR">
				<head>
					<meta charset=\"utf-8\">
					<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
					<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" 
					+ "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">
					<title>
					"""+titleHTML+"""
					</title>
				</head>
				<body>
					<div class="row align-items-start">
						"""
						+generateDIVs(content)+
						"""
					</div>
				</body>
				</html>
				""";
		writer.append(html);
		writer.close();
	}
	
	public void openHTMLFile(File file) {
		Desktop desktop = null;
		desktop = Desktop.getDesktop();
		URI url = null;
		
		try {
			url = new URI(file.toURI().toString());
			desktop.browse(url);
			
		}catch(URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private String generateDIVs(List<? extends Content> content) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < content.size(); i++) {
			sb.append(generateDIV(
					content.get(i).titles(), 
					content.get(i).years(), 
					content.get(i).urlImage(), 
					content.get(i).rating()));
		}
		return sb.toString();
	}
	
	private String generateDIV(String title, String years, String image, String rating) {
		String divTemplate =
				"""
				<div class="col-sm-2">
					<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
						<h4 class=\"card-header\">%s</h4>
						<div class=\"card-body\">
							<img class=\"card-img\" src=\"%s\" alt=\"%s\">
							<p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
						</div>
					</div>
				</div>
				""";
		return String.format(divTemplate, title, image, title, rating, years);
	}
}