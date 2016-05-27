import org.junit.*;

import java.util.*;

import play.test.*;
import models.*;

public class BasicTest extends UnitTest {
	
	@Test
	public void createAndRetrieveUser() {
		new Administrador("the__alvaro@hotmail.com", "123").save();
	}

}
