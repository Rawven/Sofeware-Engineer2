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
    private Random random;

    public ExerciseGenerator(int range, int numberOfExercises) {
        this.range = range;
        this.numberOfExercises = numberOfExercises;
        this.generatedExercises = new HashSet<>();
        this.random = new Random();
    }

    public void generateExercises() {
        try (BufferedWriter exerciseWriter = new BufferedWriter(new FileWriter("Exercises.txt"));
             BufferedWriter answerWriter = new BufferedWriter(new FileWriter("Answers.txt"))) {

            for (int i = 0; i < numberOfExercises; i++) {
                String exercise = generateUniqueExercise();
                if (exercise != null) {
                    double answer = evaluateExpression(exercise); // 使用新评估方法
                    exerciseWriter.write(exercise + " = \n");
                    answerWriter.write(answer + "\n");
                }
            }

        } catch (IOException e) {
            System.err.println("An error occurred while generating exercises: " + e.getMessage());
        }
    }

    public String generateUniqueExercise() {
        String exercise;
        do {
            exercise = generateRandomExpression();
        } while (generatedExercises.contains(exercise) || exercise == null);
        generatedExercises.add(exercise);
        return exercise;
    }

    private String generateRandomExpression() {
        int numOperators = random.nextInt(3) + 1; // 1 to 3 operators
        List<String> expressionParts = new ArrayList<>();

        for (int i = 0; i <= numOperators; i++) {
            expressionParts.add(generateRandomNumber());
            if (i < numOperators) {
                expressionParts.add(String.valueOf(OPERATORS.charAt(random.nextInt(4))));
            }
        }

        return String.join(" ", expressionParts);
    }

    private String generateRandomNumber() {
        if (random.nextBoolean()) {
            return String.valueOf(random.nextInt(range));
        } else {
            int numerator = random.nextInt(range - 1) + 1;
            int denominator;
            do {
                denominator = random.nextInt(range - 1) + 1; // Ensure denominator is not zero
            } while (denominator == 0);
            return numerator + "/" + denominator; // Proper fraction
        }
    }

    public static double evaluateExpression(String expression) {
        // 新的表达式评估逻辑
        // 使用之前提供的 ExpressionEvaluator 类
        return ExpressionEvaluator.evaluate(expression);
    }
}