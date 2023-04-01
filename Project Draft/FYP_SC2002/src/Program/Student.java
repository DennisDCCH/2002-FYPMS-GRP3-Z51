package Program;

import java.util.ArrayList;

public abstract class Student extends User {

	private ArrayList<String> deregisteredProject;
	
	public Student(UserReadFile file) 
	{
		super(file);
		this.deregisteredProject = new ArrayList<String>();
		
	}
	
	public ArrayList<String> getDeregisteredProject()
	{
		return this.deregisteredProject;
	}
	
	public abstract boolean haveProject();
	
	public abstract void menu();

}
