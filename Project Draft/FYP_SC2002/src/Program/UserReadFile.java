package Program;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserReadFile implements ReadFileInterface{
	
	private ArrayList<List> userInfo;

	public UserReadFile()
	{
		this.userInfo = new ArrayList<List>();

	}
	
	// read csv file
	public void readCSV(String path,String fileName)
	{
		
		String delimiter = ","; // delimiter
	    String line;
		List<List<String>> lines = new ArrayList(); // ArrayList to store each line of csv file
		
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> email = new ArrayList<String>();
		ArrayList<String> userID = new ArrayList<String>();
		
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(path+fileName));

           while((line = br.readLine()) != null){
               List<String> values = Arrays.asList(line.split(delimiter));
               lines.add(values);
           }
           
        
           for (int i=1;i<lines.size();i++)
           {
        	   name.add(lines.get(i).get(0)); // get name
        	   String[] temp = lines.get(i).get(1).split(";");
        	   email.add(temp[0]); // get email
           }
           
           for (String n: email)
           {
        	   String[] temp = n.split("@");
        	   userID.add(temp[0]); // get userID
           }
          
           
           for (int i =0; i<email.size();i++)
           {
        	   List<String> information = new ArrayList<>();
        	   information.add(userID.get(i)); // append userID
        	   information.add(email.get(i)); // append email
        	   information.add(name.get(i)); // append name
        	   userInfo.add(information);
        	   System.out.println(userInfo.get(i));
        	   
           }
            
		}
		
		// catching exceptions
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<List> getUserInfo()
	{
		return userInfo;
	}


}
