package departamentoshibernate;

/**
 *
 * @author nurmu
 */
public class MisExcepciones extends Exception {

    private String error;

    public MisExcepciones() {
    }

    public MisExcepciones(String message) {
        super(message);
        this.error = message;
    }

    public String getError() {
        return error;
    }

}
