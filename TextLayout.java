

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TextLayout {

    // Layout text.
    // Lay out the text contained in the List<String> words into the individual lines.
    private static List<String> layoutText(List<String> words, int maxLineWidth) {
	//return greedyLayoutText(words, maxLineWidth);
	// TODO: Uncomment this after implementing prettyPrintLayoutText()
	return prettyPrintLayoutText(words, maxLineWidth);
    }

    // Layout the text minimizing the "pretty print" cost function.
    private static List<String> prettyPrintLayoutText(List<String> words, int maxLineWidth) {
	List<String> lines = new ArrayList<String>();

	////////////////////////////////
	// TODO: Write your code here. //
	////////////////////////////////

  //Create Map of Subproblems @Shrijan
	String each_subproblem = "";
	HashMap<String, Integer> subproblemMap = new HashMap<String, Integer>();
	for (int i = lines.size(); i>=0; i--){
		each_subproblem += lines.get(i) + " ";
		subproblemMap.put(each_subproblem.trim(), i);
	}

	return lines;
    }

    public static void main(String[] args) {
	if (args.length < 2) {
	    System.out.println("Expecting 2 args: text-path and max-line-width.");
	}
	String textPath = args[0];
	int maxLineWidth = Integer.parseInt(args[1]);

	List<String> words = new ArrayList<String>();
	// Read the words from the file, and create a List with the individual words.
	try {
	    List<String> lines = Files.readAllLines(Paths.get(textPath),
						    StandardCharsets.UTF_8);
	    Scanner in = new Scanner(System.in);
	    for (String line : lines) {
		for (String token : line.trim().split("\\s+")) {
		    words.add(token);
		}
	    }
	} catch (IOException ex) {
	    ex.printStackTrace();
	}

	List<String> lines = layoutText(words, maxLineWidth);
	int lineNum = 1;
	int totalCost = 0;
	int lastLineCost = 0;
	for (String line : lines) {
	    totalCost += lastLineCost;
	    lastLineCost = (maxLineWidth - line.length()) * (maxLineWidth - line.length());
	    while (line.length() < maxLineWidth) line += " ";
	    line += "|";
	    System.out.format("%4d  %s\n", lineNum++, line);
	}
	System.out.println("Total cost: " + totalCost);
    }
}
