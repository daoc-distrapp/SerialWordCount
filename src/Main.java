import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Contador de palabras serial
 * @author dordonez@ute.edu.ec
 */
public class Main {

	public static void main(String[] args) throws IOException {
		File f = new File("file.txt");
		Map<String, Integer> total = new HashMap<String, Integer>();
		
		//scanner(f);
		//bufferedReader(f);
		
		for(String line : fileToLines(f)) {
			String[] words = splitTrimLine(line);
			Map<String, Integer> parcial = wordsArrayIntoMap(new HashMap<String, Integer>(), words);
			wordsMapIntoMap(total, parcial);		
		}
		total.entrySet().stream().map(e -> e.toString()).sorted().forEach(System.out::println);
	}

	public static List<String> fileToLines(File f) throws IOException {
		return Files.readAllLines(f.toPath());
	}
	
	public static void scanner(File f) throws FileNotFoundException {
		Scanner scan = new Scanner(f);
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			System.out.println(line);
		}
		scan.close();		
	}
	
	public static void bufferedReader(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		while((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}	
	
	public static String[] splitTrimLine(String line) {
		return line.split("\\W+");
	}
	
	public static Map<String, Integer> wordsArrayIntoMap(Map<String, Integer> total, String[] words) {
		for(String w : words) {
			if(!total.containsKey(w)) {
				total.put(w, 1);
			} else {
				int count = total.get(w);
				total.put(w, count + 1);
			}
		}
		return total;
	}
	
	public static Map<String, Integer> wordsMapIntoMap(Map<String, Integer> total, Map<String, Integer> partial) {
		for(String w : partial.keySet()) {
			int partialcount = partial.get(w);
			if(!total.containsKey(w)) {
				total.put(w, partialcount);
			} else {
				int count = total.get(w);
				total.put(w, count + partialcount);
			}
		}
		return total;
	}	
	
}
