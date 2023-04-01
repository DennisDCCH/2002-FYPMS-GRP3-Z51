package Program;

public class studentHaveProject extends Student {

	public studentHaveProject(UserReadFile file) 
	{
		super(file);
	}
	
	public boolean haveProject()
	{
		return true;
	}
	
	@Override
	public void menu()
	{
		System.out.println("========================================");
		System.out.println("|1. Change Password                    |");
		System.out.println("|2. View Registered Project            |");
		System.out.println("|3. View Available Projects            |");
		System.out.println("|4. View Request History and Status    |");
		System.out.println("|5. Request to Change Project Title    |");
		System.out.println("|6. Request to Deregister FYP          |");
		System.out.println("|7. Logout                             |");
		System.out.println("========================================");
	}

}
