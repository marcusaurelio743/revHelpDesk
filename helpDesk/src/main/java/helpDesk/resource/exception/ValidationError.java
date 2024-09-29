package helpDesk.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMensage> errors = new ArrayList<FieldMensage>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMensage> getErrors() {
		return errors;
	}

	public void AddError(String name, String menssage) {
		this.errors.add(new FieldMensage(name, menssage));
	}
	
	

}
