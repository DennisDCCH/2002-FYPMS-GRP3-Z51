package display;

import java.util.Scanner;

import enumclass.ProjectStatus;
import enumclass.RequestStatus;
import enumclass.RequestType;
import models.Project;
import models.ProjectList;
import models.Request;
import models.RequestList;
import models.Supervisor;
import models.SupervisorList;
import models.TransferStudentRequest;

/**
 * This class is the supervisor menu for the FYP management system.
 * @author Dennis Chen
 * @version 1.0
 *
 */
public class SupervisorApp {
	
	/**
	 * This method have all the functions that a supervisor can access in the FYP management system.
	 * @param supervisor this is the supervisor object that that is entered into the system after the user log in.
	 */
	public static void display(Supervisor supervisor) {
		Scanner sc = new Scanner(System.in);
		
		int choice;
		do {
			mainDisplay(supervisor);
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
				// Change Password
				case 1:
					String password;
					while(true) {
						System.out.println("Enter new password: ");
						password = sc.nextLine();
						if(password.contains(",")) 
							System.out.println("Password cannot contain commas (,)");
						else
							break;
					}
					supervisor.setPassword(password);
					System.out.println("Password changed\n");
					break;
					
				// My Projects
				case 2:
					supervisorProjectDisplay(supervisor);
					break;
					
				// Request
				case 3:
					supervisorRequestDisplay(supervisor);
					break;
				
				// Request to transfer Student
				case 4:
					boolean bool = false;
					int projectID;
					String userID;
					if(supervisor.getAllocatedProjectList().size() > 0) {
						supervisor.printAllocatedProjects();
						while(true) {
							System.out.println("Enter the projectID that you want to transfer: ");
							projectID = sc.nextInt();
							if(supervisor.getAllocatedProject(projectID) != null) {
								break;
							}
							else {
								System.out.println("You have entered a invalid project ID!");
								System.out.println("Please try again!\n");
							}
						}
						
						for(Request r: supervisor.getRequestList()) {
							if(r.getType() == RequestType.TRANSFER_STUDENT) {
								if(r.getStatus() == RequestStatus.PENDING) {
									if(r.getProjectID() == projectID)
										bool = true;
								}
							}
						}
						if(bool) {
							System.out.println("Project ID " + projectID + " is already waiting for approval for transfer!");
							break;
						}
						while(true) {
							System.out.println("Enter the new supervisor USERID");
							userID = sc.next();
							if(SupervisorList.getSpecificSupervisorID(userID) != null) {
								if(SupervisorList.getSpecificSupervisorID(userID).getSupervisingProjectCount() < 2) {
									//Make the new request
									Request r = new TransferStudentRequest(RequestList.getNextRequestID(), supervisor.getUserName(), "Li Fang", projectID, RequestStatus.PENDING, userID);
									
									//Append request to overall requestList and supervisor requestList
									supervisor.addRequest(r);
									RequestList.addRequest(r);
									
									System.out.println("The request have been send out.\n");
									break;
								}
								else {
									System.out.println(userID + " cannot take on more projects!");
									System.out.println("Please try again!\n");
								}
							}
							else {
								System.out.println("You have entered a invalid USERID");
								System.out.println("Please try again!\n");
							}
						}
					}
					else 
						System.out.println("You have no projects that is allocated currently");
					break;
					
				// Logout
				case 5:
					System.out.println("Logging out...\n");
					break;
					
				default:
					System.out.println("Please choose a valid option\n");
					break;
			}
		}while(choice != 5);
		
	}
	
	/**
	 * This method prints out the supervisor main menu
	 * @param supervisor this is the supervisor object that that is entered into the system after the user log in.
	 */
	private static void mainDisplay(Supervisor supervisor) {
		if(supervisor.pendingRequest()) {
			System.out.println("========================================");
			System.out.println("|1. Change Password                    |");
			System.out.println("|2. My Projects                        |");
			System.out.println("|3. Request (New)                      |");
			System.out.println("|4. Request to Transfer Student        |");
			System.out.println("|5. Logout                             |");
			System.out.println("========================================");
			System.out.println("Enter your choice: ");
		}
		else{
			System.out.println("========================================");
			System.out.println("|1. Change Password                    |");
			System.out.println("|2. My Projects                        |");
			System.out.println("|3. Request                            |");
			System.out.println("|4. Request to Transfer Student        |");
			System.out.println("|5. Logout                             |");
			System.out.println("========================================");
			System.out.println("Enter your choice: ");
		}
	}
	
	/**
	 * This method prints out the supervisor project menu. In the project menu, it will contain different functions
	 * for the supervisor.
	 * @param supervisor this is the supervisor object that that is entered into the system after the user log in.
	 */
	private static void supervisorProjectDisplay(Supervisor supervisor) {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		do {
			System.out.println("========================================");
			System.out.println("|1. Create Projects                    |");
			System.out.println("|2. Update Projects                    |");
			System.out.println("|3. View Projects                      |");
			System.out.println("|4. Return                             |");
			System.out.println("========================================");
			System.out.println("Enter your choice: ");
			option = sc.nextInt();
			sc.nextLine();
			switch(option) {
				// Create Projects
				case 1:
					String projectTitle;
					while(true) {
						System.out.println("Enter Project Title: ");
						projectTitle = sc.nextLine();
						if(projectTitle.contains(",")) 
							System.out.println("Title cannot contain commas (,)");
						else
							break;
					}
					
					Project p = new Project(ProjectList.getNextProjectID(), supervisor.getUserName(), supervisor.getEmail(), 
							null, null, projectTitle, ProjectStatus.AVAILABLE);
					
					// Adding to supervisor list of projects
					supervisor.addProject(p);
					// Adding to the overall list of projects
					ProjectList.addProject(p);
					//If already have 2 allocated project need to set this new project as unavailable
					supervisor.checkAndSetProjectStatus();
					
					System.out.println("Project created!\n");
					break;
					
				// Update Projects
				case 2:
					if(supervisor.getProjectList().size() > 0) {
						String title;
						supervisor.printMyProjects();
						System.out.println("Enter the Project ID to update");
						int id = sc.nextInt();
						sc.nextLine();
						if(supervisor.getProject(id) != null) {
							while(true) {
								System.out.println("Enter the new project title: ");
								title = sc.nextLine();
								if(title.contains(",")) 
									System.out.println("Title cannot contain commas (,)");
								else
									break;
							}
							supervisor.getProject(id).setProjectTitle(title);
							System.out.println("Your project have been updated!");
						}
						else {
							System.out.println("You have entered a invalid project ID!");
							System.out.println("Returning back to main menu...\n");
						}
					}
					else
						System.out.println("You have no projects\n");
					break;
					
				// View Projects
				case 3:
					if(supervisor.getProjectList().size() > 0)
						supervisor.printMyProjects();
					else
						System.out.println("You have no projects\n");
					break;
					
				// Return
				case 4:
					System.out.println("Returning... \n");
					break;
					
				default:
					System.out.println("Please choose a valid option\n");
					break;
			}
		}while(option != 4);
			
	}
	
	/**
	 * This method will contain different request functions for the supervisor.
	 * @param supervisor this is the supervisor object that that is entered into the system after the user log in.
	 */
	private static void supervisorRequestDisplay(Supervisor supervisor) {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		boolean bool = true;
		do {
			if(supervisor.pendingRequest()) {
				requestDisplay(supervisor);
				option = sc.nextInt();
				switch(option) {
					// Pending Request
					case 1:
						supervisor.printPendingRequest();
						System.out.println("Enter the request ID to process: ");
						int id = sc.nextInt();
						if(supervisor.getRequest(id) != null) {
							supervisor.getRequest(id).processRequest();
						}
						else {
							System.out.println("You have entered a invalid request ID!");
							System.out.println("Returning back to main menu...\n");
						}
						break;
						
					// Request History and Status
					case 2:
						if(supervisor.getRequestList().size() > 0)
							supervisor.printMyRequest();
						else
							System.out.println("You have no request history");
						break;
						
					// Return
					case 3:
						System.out.println("Returning... \n");
						bool = false;
						break;
						
					default:
						System.out.println("Please choose a valid option\n");
						break;	
				}
			}
			else {
				requestDisplay(supervisor);
				option = sc.nextInt();
				switch(option) {
					// Request History and Status
					case 1:
						if(supervisor.getRequestList().size() > 0)
							supervisor.printMyRequest();
						else
							System.out.println("You have no request history");
						break;
						
					// Return	
					case 2:
						System.out.println("Returning... \n");
						bool = false;
						break;
						
					default:
						System.out.println("Please choose a valid option\n");
						break;	
				}
			}
		}while(bool);
	}
	
	/**
	 * This method will print the request menu for the supervisor
	 * @param supervisor this is the supervisor object that that is entered into the system after the user log in.
	 */
	private static void requestDisplay(Supervisor supervisor) {
		if(supervisor.pendingRequest()) {
			System.out.println("========================================");
			System.out.println("|1. Pending Request                    |");
			System.out.println("|2. Request History and Status         |");
			System.out.println("|3. Return                             |");
			System.out.println("========================================");
			System.out.println("Enter your choice: ");
		}
		else {
			System.out.println("========================================");
			System.out.println("|1. Request History and Status         |");
			System.out.println("|2. Return                             |");
			System.out.println("========================================");
			System.out.println("Enter your choice: ");
		}
	}
}
