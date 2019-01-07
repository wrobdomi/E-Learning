package entities;

public class Question {

    private int questionId;
    private int quizId;
    private String question;

    public int getQuestionId() {
        return questionId;
    }

    public int getQuizId() {
        return quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
