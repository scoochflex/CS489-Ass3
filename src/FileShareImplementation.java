import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService
public class FileShareImplementation implements FileShareService{
	@Override
	public String echo(String input) {
		return input;
	}
}
