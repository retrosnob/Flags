import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {

    List<Question> questions;
    int questionCount = 0;
    int numCorrect = 0;
    int numAttempted = 0;

    Model() {
        // read the questions into my questions array
        List<String> questionStrings = new FileHelper().getFileContents();
        questions = new ArrayList<>(questionStrings.size());
        for (String q : questionStrings) {
            String[] qarry = q.split(",");
            questions.add(new Question(qarry[0], qarry[1]));
        }
    }

    public static void main(String[] args) {
        System.out.println("Model run as main");
        Model model = new Model();
        model.newQuiz();
        for (int i = 0; i < 5; i++) {
            System.out.println(model.getNextQuestion().country);
        }

        // Check that the model works fully.
    }

    void newQuiz() {
        // shuffle the questions array
        numCorrect = 0;
        numAttempted = 0;
        questionCount = 0;
        Collections.shuffle(questions);
    }

    Question getNextQuestion() {
        return questions.get(questionCount++);
    }

    List<String> getCountries() {
        List<String> countries = new ArrayList<>(questions.size());
        questions.forEach((question) -> {
            countries.add(question.country);
        });
        Collections.sort(countries);
        return countries;
    }

    String checkAnswer(String answer) {
        numAttempted += 1;
        String correct = questions.get(questionCount - 1).country;
        if (answer.equalsIgnoreCase(correct)) {
            numCorrect += 1;
        }
        return correct;
    }

    int getNumCorrect() {
        return numCorrect;
    }

    int getNumAttempted() {
        return numAttempted;
    }

}
