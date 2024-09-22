package www.Raven;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static www.Raven.Utils.parseValue;

public class ExerciseGenerator {
	private static final String OPERATORS = "+-*/";
	private int range;
	private int numberOfExercises;
	private Set<String> generatedExercises;

	public ExerciseGenerator(int range, int numberOfExercises) {
		this.range = range;
		this.numberOfExercises = numberOfExercises;
		this.generatedExercises = new HashSet<>();
	}

	public void generateExercises() {
		try (BufferedWriter exerciseWriter = new BufferedWriter(new FileWriter("Exercises.txt"));
		     BufferedWriter answerWriter = new BufferedWriter(new FileWriter("Answers.txt"))) {

			for (int i = 0; i < numberOfExercises; i++) {
				String exercise = generateUniqueExercise();
				if (exercise != null) {
					double answer = evaluateExpression(exercise);
					exerciseWriter.write(exercise + " = \n");
					answerWriter.write(answer + "\n");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String generateUniqueExercise() {
		String exercise;
		do {
			exercise = generateRandomExpression();
		} while (generatedExercises.contains(exercise) || exercise == null);
		generatedExercises.add(exercise);
		return exercise;
	}

	private String generateRandomExpression() {
		int numOperators = new Random().nextInt(3) + 1; // 1 to 3 operators
		List<String> expressionParts = new ArrayList<>();

		for (int i = 0; i <= numOperators; i++) {
			expressionParts.add(generateRandomNumber());
			if (i < numOperators) {
				expressionParts.add(String.valueOf(OPERATORS.charAt(new Random().nextInt(4))));
			}
		}

		return String.join(" ", expressionParts);
	}

	private String generateRandomNumber() {
		if (new Random().nextBoolean()) {
			return String.valueOf(new Random().nextInt(range));
		} else {
			int numerator = new Random().nextInt(range - 1) + 1; // from 1 to range-1
			int denominator = new Random().nextInt(range - 1) + 1; // from 1 to range-1
			return numerator + "/" + denominator; // Proper fraction
		}
	}

	private double evaluateExpression(String expression) {
		String[] tokens = expression.trim().split(" ");
		double result = parseValue(tokens[0]);

		for (int i = 1; i < tokens.length; i += 2) {
			String operator = tokens[i];
			double nextValue = parseValue(tokens[i + 1]);
			switch (operator) {
				case "+":
					result += nextValue;
					break;
				case "-":
					result -= nextValue;
					break;
				case "*":
					result *= nextValue;
					break;
				case "/":
						result /= nextValue;
					break;
				default:
			}
		}

		return result;
	}
}