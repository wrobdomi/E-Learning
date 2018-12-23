package entities;

public class Quizz {

    private int quizId;
    private String username;
    private String quizName;

    public int getQuizId() {
        return quizId;
    }

    public String getUsername() {
        return username;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
}
