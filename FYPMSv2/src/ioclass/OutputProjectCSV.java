package ioclass;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import models.Project;

/**
 * This class exports a new csv file based on the information attained from a list of projects
 * @author Dennis Chen
 * @version 1.0
 *
 */
public class OutputProjectCSV {
	/**
	 * This stores the filePath to get to the project csv file
	 */
	private static String filePath = System.getProperty("user.dir") + "\\CSVFiles\\project.csv";
	
	/**
	 * This method deleted the old csv file in the filepath and makes a new one based on the information attained 
	 * from a list of projects
	 * @param projectList the information based on this list of projects that is to be saved in a csv file
	 */
	public static void writeCSV(List<Project> projectList) {
		File oldFile = new File(filePath);
		oldFile.delete();
		
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filePath);
			
			fileWriter.append("Project ID, Supervisor Name, Supervisor Email, Student Name, Student Email, Project Title, Project Status");
			fileWriter.append("\n");
			for(Project p: projectList) {
				fileWriter.append(String.valueOf(p.getProjectID()));
				fileWriter.append(",");
				fileWriter.append(p.getSupervisorName());
				fileWriter.append(",");
				fileWriter.append(p.getSupervisorEmail());
				fileWriter.append(",");
				fileWriter.append(p.getStudentName());
				fileWriter.append(",");
				fileWriter.append(p.getStudentEmail());
				fileWriter.append(",");
				fileWriter.append(p.getProjectTitle());
				fileWriter.append(",");
				fileWriter.append(p.getStatus().toString());
				fileWriter.append("\n");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
