package Program;

import java.util.Scanner;

public class MainApp {
	public static void main(String[] args) {
		
		String path = "C:\\Users\\user\\Desktop\\NTU SC2002\\Project Draft\\"; // file path
		String studentFileName = "student list.csv";
		
		
		UserReadFile studentFile = new UserReadFile();
		Scanner sc = new Scanner(System.in);
		String userID;
		
		
		studentFile.readCSV(path,studentFileName);
		Student student = new studentHaveProject(studentFile);
		boolean login = false;
		while(!login)
		{
			System.out.println("Please enter your userID: ");
			userID = sc.next();
			login = student.checkDomain(userID);
			if (!login)
			{
				System.out.println("Invalid ID");
			}
		}
		student.menu();
	
	}

}
