package enums;

public class QuizMenuEnum {

    QuizOptions qo;

    public QuizMenuEnum(QuizOptions qo) {
        this.qo = qo;
    }

    public String getOption() {
        String option = null;
        switch (qo) {
            case ADDQ:
                option = "Add Question";
                break;

            case START:
                option = "Start Learning";
                break;
        }
        return option;
    }


}
