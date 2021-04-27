public class Question {
    private final String question;
    private final String answerA;
    private final String answerB;
    private final String answerC;
    private final String answerD;
    private final String correctAnswer;

    public Question(String text, String a, String b, String c, String d, String answer) {
        question = text;
        answerA = a;
        answerB = b;
        answerC = c;
        answerD = d;
        correctAnswer = answer;
    }

    public String getContent() {
        return question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getFullCorrectAnswer() {
        switch (correctAnswer) {
            case "a":
                return answerA;
            case "b":
                return answerB;
            case "c":
                return answerC;
            case "d":
                return answerD;
            default:
                return null;
        }
    }
}
