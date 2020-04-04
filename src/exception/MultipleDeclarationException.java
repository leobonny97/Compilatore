package exception;

public class MultipleDeclarationException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultipleDeclarationException(String message) {
        super("La variabile: " + message + " � gia stata dichiarata in questo scoop");
    }
}