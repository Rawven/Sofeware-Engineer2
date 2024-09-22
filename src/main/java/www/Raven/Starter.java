package www.Raven;

public class Starter {
	public static void main(String[] args) {
//        if (args.length < 2) {
//            Utils.printHelp();
//            return;
//        }

		int numberOfExercises = 10000;
		int range = 20;

		for (int i = 0; i < args.length; i++) {
			if ("-n".equals(args[i]) && i + 1 < args.length) {
				numberOfExercises = Integer.parseInt(args[++i]);
			} else if ("-r".equals(args[i]) && i + 1 < args.length) {
				range = Integer.parseInt(args[++i]);
			} else {
				Utils.printHelp();
				return;
			}
		}

		Utils.validateParameters(range, numberOfExercises);

		ExerciseGenerator generator = new ExerciseGenerator(range, numberOfExercises);
		generator.generateExercises();

		AnswerEvaluator evaluator = new AnswerEvaluator();
		evaluator.evaluateAnswers("Exercises.txt", "Answers.txt");
	}
}