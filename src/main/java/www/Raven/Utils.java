package www.Raven;

public class Utils {
	public static void printHelp() {
		System.out.println("Usage: java www.Raven.Main -n <number_of_exercises> -r <range>");
	}

	public static void validateParameters(int range, int numberOfExercises) {
		if (range < 1) {
			throw new IllegalArgumentException("Range must be a positive integer.");
		}
		if (numberOfExercises < 1) {
			throw new IllegalArgumentException("Number of exercises must be a positive integer.");
		}
	}
    
    
	public static double parseValue(String value) {
		if (value.contains("/")) {
			String[] parts = value.split("/");
			double numerator = Double.parseDouble(parts[0]);
			double denominator = Double.parseDouble(parts[1]);
			return numerator / denominator;
		} else {
			return Double.parseDouble(value);
		}
	}
}