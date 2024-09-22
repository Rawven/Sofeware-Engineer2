package www.Raven;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ExerciseGeneratorTest {

    private ExerciseGenerator exerciseGenerator;

    @Before
    public void setUp() {
        exerciseGenerator = new ExerciseGenerator(10, 5);
    }

    @Test
    public void testGenerateExercisesUnique() throws IOException {
        exerciseGenerator.generateExercises();

        // 读取生成的文件并检查唯一性
        Set<String> exercises = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Exercises.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    exercises.add(line.trim());
                }
            }
        }

        assertEquals(5, exercises.size()); // 应该生成5个唯一的练习
    }

    @Test
    public void testGenerateExercisesWithinRange() throws IOException {
        exerciseGenerator.generateExercises();

        // 检查生成的表达式中的数字是否在范围内
        try (BufferedReader br = new BufferedReader(new FileReader("Exercises.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                for (String part : parts) {
                    if (part.matches("\\d+")) {
                        int num = Integer.parseInt(part);
                        assertTrue(num >= 0 && num < 10); // 确保数字在范围内
                    } else if (part.matches("\\d+/\\d+")) {
                        String[] fractionParts = part.split("/");
                        int numerator = Integer.parseInt(fractionParts[0]);
                        int denominator = Integer.parseInt(fractionParts[1]);
                        assertTrue(numerator > 0 && denominator > 0 && numerator < 10 && denominator < 10); // 确保分数在范围内
                    }
                }
            }
        }
    }

    @Test
    public void testGenerateExercisesFileCreated() throws IOException {
        exerciseGenerator.generateExercises();
        assertTrue(Files.exists(Paths.get("Exercises.txt")));
        assertTrue(Files.exists(Paths.get("Answers.txt")));
    }

    @Test
    public void testGenerateExercisesValidOutput() throws IOException {
        exerciseGenerator.generateExercises();

        // 检查答案文件是否包含有效的答案
        try (BufferedReader br = new BufferedReader(new FileReader("Answers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                assertNotNull(line);
                assertFalse(line.trim().isEmpty()); // 确保答案不为空
            }
        }
    }

    @Test
    public void testGenerateUniqueExercise() {
        String exercise = exerciseGenerator.generateUniqueExercise();
        assertNotNull(exercise);
        assertFalse(exercise.isEmpty());
    }
}