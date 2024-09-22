package www.Raven;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static www.Raven.ExerciseGenerator.evaluateExpression;
import static www.Raven.Utils.parseValue;

public class AnswerEvaluator {
	public void evaluateAnswers(String exerciseFile, String answerFile) {
		try (BufferedReader exerciseReader = new BufferedReader(new FileReader(exerciseFile));
		     BufferedReader answerReader = new BufferedReader(new FileReader(answerFile));
		     BufferedWriter gradeWriter = new BufferedWriter(new FileWriter("Grade.txt"))) {

			String exercise;
			String answer;
			List<Integer> correctIndexes = new ArrayList<>();
			List<Integer> wrongIndexes = new ArrayList<>();
			int index = 1;

			while ((exercise = exerciseReader.readLine()) != null && (answer = answerReader.readLine()) != null) {
				double expectedAnswer = evaluateExpression(exercise.replace("=", "").trim());

				if (Double.parseDouble(answer) == expectedAnswer) {
					correctIndexes.add(index);
				} else {
					wrongIndexes.add(index);
				}
				index++;
			}

			gradeWriter.write("Correct: " + correctIndexes.size() + " (" + correctIndexes + ")\n");
			gradeWriter.write("Wrong: " + wrongIndexes.size() + " (" + wrongIndexes + ")\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 

}