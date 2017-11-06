
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class JUnitExecutionListener extends RunListener{

	
	@Override
	public void testRunStarted(Description description) throws Exception {
		// TODO Auto-generated method stub
		super.testRunStarted(description);
	}

	@Override
	public void testStarted(Description description) throws Exception {
		// TODO Auto-generated method stub
		super.testStarted(description);
		System.out.println(description.getMethodName());
	}

}
