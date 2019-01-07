package entities;

public class Answer {

    private int answerId;
    private int questionId;
    private String answer;
    private int correct;

    public int getAnswerId() {
        return answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public int getCorrect() {
        return correct;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
