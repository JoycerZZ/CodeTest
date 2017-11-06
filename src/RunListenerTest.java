
import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RunListenerTest {
	public void Add(ArrayList<String> s , int a){
		s.add("dsdsad");
		a++;
	}
    @Test
    //@Order(order = 2)
    public void d_testListener(){

    }

    @Test
    //@Order(order = 3)
    public void b_testException(){
        int a = 9;
    }
    
    @Test
    //@Order(order = 1)
    public void a_testException1(){
        int a = 9;
    }
    
    @Test
    //@Order(order = 4)
    public void c_testException2(){
        int a = 9;
        ArrayList<String> s = new ArrayList<>();
        Add(s , a);
        System.out.println(s.toString()+a);
    }
}
