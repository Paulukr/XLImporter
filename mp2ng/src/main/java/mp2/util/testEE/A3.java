package mp2.util.testEE;

import java.util.HashMap;
import java.util.Map;

public class A3 {

	public A3() {
		// TODO Auto-generated constructor stub
	}
	Map<String, String> commandsMap = new HashMap<>();
	
    private void mapInit() {
        commandsMap.put(UrlHolder.COURSES, "cou");
//        commandsMap.put(UrlHolder.PATH_LOGOUT, new CommandMock());
//        commandsMap.put(UrlHolder.PATH_UNBLOCK, new CommandMock());
//        commandsMap.put(UrlHolder.PATH_FIND_ACCOUNT, new CommandMock());
//        commandsMap.put(UrlHolder.PATH_PAY, new CommandMock());
//        commandsMap.put(UrlHolder.PATH_REFILL, new CommandMock());
//        commandsMap.put(UrlHolder.PATH_BLOCK, new CommandMock());
        commandsMap.put(UrlHolder.PAGE_NOT_FOUND, "pnf");
        commandsMap.put("test", "test");
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		A3 a3 = new A3();
	String nString = null;
		System.out.println(nString);
	}

}
