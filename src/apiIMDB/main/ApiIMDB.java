package apiIMDB.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import apiIMDB.main.model.ImdbApiClient;
import apiIMDB.main.model.ImdbMovieJsonParser;
import apiIMDB.main.view.HTMLGenerator;

public class ApiIMDB {

	public static void main(String[] args) {

//		String apiKey = API_KEY.apiKey;
//		List<Filme> dadosFilmes = new ArrayList<>();
//
//		String response = httpRequest("https://imdb-api.com/en/API/Top250Movies/", apiKey);
		
//		List<String> ranks = listarDados(response, "rank");
//		List<String> titles = listarDados(response, "title");
//		List<String> year = listarDados(response, "year");
//		List<String> image = listarDados(response, "image");
//		List<String> imDbRating = listarDados(response, "imDbRating");
//
//		for (int i = 0; i < ranks.size(); i++) {
//			Filme filme = new Filme(Integer.parseInt(ranks.get(i)), titles.get(i), Integer.parseInt(year.get(i)),
//					image.get(i), Double.parseDouble(imDbRating.get(i)));
//			dadosFilmes.add(filme);
//		}
		
//		HTMLGenerator html = new HTMLGenerator();
//		File file = printWriter(html.generateHTML(dadosFilmes));
//		openHTML(file);
		
		
		try {
			File file = new File("testHTML.html");
			PrintWriter writer = new PrintWriter(file);
			
			String response = new ImdbApiClient("https://imdb-api.com/en/API/Top250Movies/").httpRequest();
			List<Filme> dadosFilmes = new ImdbMovieJsonParser(response).parse();

			HTMLGenerator htmlGen = new HTMLGenerator(writer);
			htmlGen.generateHTML(dadosFilmes);
			htmlGen.openHTMLFile(file);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void teste(String str) {
		try{
			PrintWriter writer = new PrintWriter(new File("out.txt"));
			writer.write(str);
			writer.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
//	private static File printWriter(String data) {
//		try {
//			File file = new File("testHTML.html");
//			PrintWriter writer = new PrintWriter(file);
//			writer.write(data);
//			writer.close();
//			return file;
//			
//		}catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//	private static void openHTML(File file) {
//		Desktop desktop = null;
//		desktop = Desktop.getDesktop();
//		URI url = null;
//		
//		try {
//			url = new URI(file.toURI().toString());
//			desktop.browse(url);
//			
//		}catch(URISyntaxException | IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	public static record Filme(Integer ranks, String titles, Integer years, String image, Double imDbRating) {

		@Override
		public String toString() {
			if (ranks <= 9) {
				return "[0" + ranks + "] | Título:" + titles + "| Ano de Lançamento:" + years + " | image=" + image
						+ " | imDbRating=" + imDbRating + "]";
			} else {
				return "[" + ranks + "] | Título:" + titles + "| Ano de Lançamento:" + years + " | image=" + image
						+ " | imDbRating=" + imDbRating + "]";
			}
		}
	}

//	private static List<String> listarDados(String responseJson, String chave) {
//		List<String> list = new ArrayList<>();
//		String newStr = "";
//
//		// Retira partes desnecessárias da String.
//		Pattern pattern = Pattern.compile("\\[(.+?)\\]");
//		Matcher matcher = pattern.matcher(responseJson);
//
//		// Joga em uma nova String.
//		while (matcher.find()) {
//			newStr = matcher.group(1);
//		}

//		// Pega os dados de uma chave específica ex: Chave Nome pega o valor Matheus Proença.
//
//		Pattern pChave = Pattern.compile("\"" + chave + "\":\\s*[\"](.*?)[\"]\\s*[,}]");
//		Matcher mChave = pChave.matcher(responseJson);
//
//		Matcher mChave = Pattern.compile("\"" + chave + "\":\\s*[\"](.*?)[\"]\\s*[,}]").matcher(responseJson);
//
//		while (mChave.find()) {
//			list.add(mChave.group(1));
//		}
//
//		return list;
//	}

//	private static String httpRequest(String URI, String apiKey) {
//		try {
//			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URI + apiKey))
//					.header("Content-Type", "application/json").GET().build();
//
//			HttpClient httpClient = HttpClient.newBuilder().build();
//
//			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
//
//			return response.body();
//
//		} catch (URISyntaxException e) {
//			return "Erro" + e.getMessage();
//		} catch (IOException e) {
//			return "Erro" + e.getMessage();
//		} catch (InterruptedException e) {
//			return "Erro" + e.getMessage();
//		}
//	}
}