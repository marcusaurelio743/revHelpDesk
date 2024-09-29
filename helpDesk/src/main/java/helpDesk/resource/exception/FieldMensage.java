package helpDesk.resource.exception;

import java.io.Serializable;

public class FieldMensage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String field;
	private String message;

	public FieldMensage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FieldMensage(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
