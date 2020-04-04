package exception;

public class NotDeclarationException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotDeclarationException(String message) {
        super("La variabile: " + message + " non è mai stata dichiarata");
    }
}
