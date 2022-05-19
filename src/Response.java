

import java.util.List;

public class Response {
	private List<Area> location;
	private String error;

	public List<Area> getLocation() {
		return location;
	}

	public String getError() {
		if (error == null)
			return "";
		return error;
	}

	@Override
	public String toString() {
		return location + getError();
	}
}
