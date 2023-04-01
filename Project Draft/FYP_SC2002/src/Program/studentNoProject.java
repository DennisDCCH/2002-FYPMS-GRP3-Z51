package Program;

public class studentNoProject extends Student {

	public studentNoProject(UserReadFile file) 
	{
		super(file);
	}
	
	public boolean haveProject()
	{
		return false;
	}
	
	@Override
	public void menu()
	{
		System.out.println("========================================");
		System.out.println("|1. Change Password                    |");
		System.out.println("|2. View Available Projects            |");
		System.out.println("|3. View Request History and Status    |");
		System.out.println("|4. Request to Register FYP            |");
		System.out.println("|5. Logout                             |");
		System.out.println("========================================");
	}
}
