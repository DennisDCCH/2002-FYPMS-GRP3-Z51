package Program;

import java.util.Scanner;

public class MainApp {
	public static void main(String[] args) {
		
		String path = "C:\\Users\\user\\Desktop\\NTU SC2002\\Project Draft\\"; // file path
		String studentFileName = "student list.csv";
		String facultyFileName = "faculty_list.csv";
		String coordinatorFileName = "FYP coordinator.csv";
		String projectFileName = "rollover project.csv";
		
		
		
		
		Scanner sc = new Scanner(System.in);
		UserReadFile file = new UserReadFile();
		
		// selecting user type
		System.out.println("Select User Type");
		System.out.println("---------------------");
		System.out.println("1. Student");
		System.out.println("2. Supervisor");
		System.out.println("3. Coordinator");
		System.out.println("---------------------");
		System.out.println("Enter your response:");
		int userType = sc.nextInt();
		
		
		
		do
		{
			switch(userType)
			{
				case 1: 
					file.readCSV(path,studentFileName);
					
					break;
				case 2:
					file.readCSV(path,facultyFileName);
					break;
				case 3:
					file.readCSV(path,coordinatorFileName);
					break;
				default:
					System.out.println("Invalid Selection");
					
				
			}
		} while ((userType<1) || (userType>3));
		
		
		
		
		if (userType == 1)
		{
			User student = new Student(file);
			
			boolean login = false;
			while(!login)
			{
				System.out.println("Please enter your userID: ");
				String userID = sc.next();
				login = student.checkDomain(userID);
				if (!login)
				{
					System.out.println("Invalid ID");
				}
			}
			login = false;
			while(!login)
			{
				System.out.println("Please enter your password ");
				String password = sc.next();
				login = student.checkPassword(password);
				if (!login)
				{
					System.out.println("Invalid password");
				}
			}
			
			System.out.println("Login Success");
			((Student)student).menu();
			
		}
		
		else if (userType == 2)
		{
			User supervisor = new Supervisor(file);
		}
		
		else
		{
			User coordinator = new Coordinator(file);
			
		}
		

		
		
		


}
}
