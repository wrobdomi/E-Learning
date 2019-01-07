package enums;

public class QuizMenuPicturesEnum {

    QuizOptions qo;

    public QuizMenuPicturesEnum(QuizOptions qo) {
        this.qo = qo;
    }

    public String getOption() {
        String picture = null;
        switch (qo) {
            case ADDQ:
                picture = "question.png";
                break;

            case START:
                picture = "startquiz.png";
                break;
        }
        return picture;
    }

}
