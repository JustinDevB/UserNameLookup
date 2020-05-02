package justindevb.lookup;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Core {

	private static String userName = "TestUser";
	private static ArrayList<String> found = new ArrayList<String>();

	public static void main(String args[]) throws IOException, URISyntaxException {

		if (args.length == 0)
			noUserInput();
		else
			setUserName(args[0]);

		ArrayList<String> sites = parseURL(loadFile());
		int x = 0;
		for (int i = 0; i < sites.size(); i++) {
			if (lookup(sites.get(i))) {
				System.out.println(sites.get(i));
				found.add(sites.get(i));
				x++;
			}
		}

		saveResults(found, x);
		System.out.println("Total found: " + x);

	}

	private static boolean lookup(String url) throws IOException, MalformedURLException { 
		try {
			URL u = new URL(url);
			HttpURLConnection huc;
			huc = (HttpURLConnection) u.openConnection();
			huc.setRequestMethod("GET");
			huc.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
			huc.setInstanceFollowRedirects(true);
			huc.connect();
			int code = huc.getResponseCode();
	
			huc.disconnect();
			return code == HttpURLConnection.HTTP_OK;
		
		} catch(IOException e) {
			System.out.println("Error with url: " + url);
			e.printStackTrace();
		}

		return false;
		
	}

	private static void noUserInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a username to lookup or type 'Q' to exit");
		String input = scanner.nextLine();

		if (input.equalsIgnoreCase("Q"))
			System.exit(0);

		setUserName(input);

	}

	private static ArrayList<String> loadFile() throws URISyntaxException {
		try {
			
			ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("Websites.txt")));
			return lines;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static ArrayList<String> parseURL(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i).toString().replace("{username}", getUserName());
			list.set(i, str);
		}

		return list;
	}

	private static void saveResults(ArrayList list, int total) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/" + getUserName() + ".txt"));
		
		for (int i = 0; i < list.size(); i++)
			writer.write(list.get(i).toString() + "\n");
		
		writer.write("\nTotal results found: " + total);
		writer.close();
	}

	private static void setUserName(String name) {
		userName = name;
	}

	private static String getUserName() {
		return userName;
	}

}
