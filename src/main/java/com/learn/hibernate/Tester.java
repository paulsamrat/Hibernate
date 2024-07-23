package com.learn.hibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester {
	public static void main(String[] args) throws IOException {
		String data = "{\r\n"
				+ "  \"insights\": [\r\n"
				+ "    \"    \"This JSON represents a sorter transformation, which is currently active and set to be case sensitive. The null values are set to be the highest and the scope is set to all input. The sorter transformation is sorting based on the field \\\"Field1\\\" in ascending order.\\n\\nThe transformation also includes a sorter data interface named \\\"Group\\\", which allows both input and output. The data interface includes four fields: Field1, Field2, Field3, and Field4. Field1 and Field3 are of decimal data type and Field2 and Field4 are of string data type. All these fields are enabled for both input and output.\\n\\nHere is an example of how this transformation would work:\\n\\nInput Table:\\n| Field1 | Field2 | Field3 | Field4 |\\n|--------|--------|--------|--------|\\n| 3.2    | B      | 4.1    | D      |\\n| 1.5    | A      | 2.3    | C      |\\n| 2.8    | C      | 3.5    | A      |\\n\\nOutput Table after Sorter Transformation:\\n| Field1 | Field2 | Field3 | Field4 |\\n|--------|--------|--------|--------|\\n| 1.5    | A      | 2.3    | C      |\\n| 2.8    | C      | 3.5    | A      |\\n| 3.2    | B      | 4.1    | D      |\\n\\nThe output table is sorted based on Field1 in ascending order as per the transformation configuration.\"\"\r\n"
				+ "  ]\r\n"
				+ "}";
		BufferedReader reader = new BufferedReader(new StringReader(data));
		String line = reader.readLine();// skip first line
		
		line = reader.readLine();
		line = extractBetweenFirstAndLastQuotes(line);
		System.out.println(line.toUpperCase().trim());

		line = reader.readLine();
		line = extractBetweenFirstAndLastQuotes(line.trim());
		line = format(line);
		System.out.println(line);

		reader.close();

	}

	private static String format(String line) {
		line = replaceQuotedWithBrackets(line);
		line = line.replace("\\n", "\n");
		line = line.replaceAll("(?<!\\d)\\.(?!\\d)", ".\n");
		return line;
	}

	public static String replaceQuotedWithBrackets(String input) {
		Pattern pattern = Pattern.compile("\\\\\"(.*?)\\\\\"");
		Matcher matcher = pattern.matcher(input);

		// Replace all occurrences
		return matcher.replaceAll("[$1]");
	}

	public static String extractBetweenFirstAndLastQuotes(String input) {
		int index1 = input.indexOf('"');
		int index2 = input.lastIndexOf('"');

		return input.substring(index1 + 1, index2);
	}
	
}
