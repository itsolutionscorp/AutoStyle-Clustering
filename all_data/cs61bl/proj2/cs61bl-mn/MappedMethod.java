
import java.io.Serializable;

public interface MappedMethod extends Serializable {
	public void execute();
	public void execute(String arg);
}

