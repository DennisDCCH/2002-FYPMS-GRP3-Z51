package Program;

public class NoRequest implements SupervisorAppInterface {

	@Override
	public void menu() 
	{
		System.out.println("========================================");
		System.out.println("|1. Change Password                    |");
		System.out.println("|2. Projects                           |");
		System.out.println("|3. Request                            |");
		System.out.println("|4. Request to Transfer Student        |");
		System.out.println("|5. Logout                             |");
		System.out.println("========================================");
		
	}
	

}
