/*Name: Corie Bain, Piranaven Selvathayabaran, Darsikan Anandarajah
MacID: bainc2, selvatp, anandad
Student Number: 001436514, 001419766, 001226159
Description: E-commerce Application | Done with Bonus #1 and #2 included.*/
import java.io.*;
//The User class works with methods P2 and P3 and handles sign in and signup.
//Testing if users exist and creating users were not done in P2 and P3 to avoid code repetition. 
//The User Class checks if the user already exists. If they do, it returns their username. If they don't then 
//No Access is displayed and then P1 is executed.
//The User Class also helps with the signing up of new users.
public class User {
	private String username;
	public String State = "P1"; //This state means that the user is at the signin/signup page P1
								//This string is public and is modified depending on the flow of the program needed.
	public int position;
	public String getUsername(String name) {
		try {
			if (State.equals("P1")) { //if the user is at the sign in/sign up page
				this.username = name; // Stores the username
				FileReader fileread = new FileReader("Users.txt");
				BufferedReader buffread = new BufferedReader(fileread);
				String readString=""; //used along with the while loop below to search Users.txt for the current username entered by the user.
				while (readString != null) // test for the end of the file
				{
					readString = buffread.readLine();
					if (username.equals(readString)) { //if the username entered matches a username on file
						State = "P3"; // State changes indicating username was found.
						System.out.println("Looking.......Username Found!");
						readString = buffread.readLine();
						buffread.close();
						return username; //found username and it is returned.
					}					
				}
				buffread.close();
				State = "P4";
				UserInterface goback = new UserInterface();
				goback.changeCurrentPage(4); // Redirects user back to P4
			}
			if (State.equals("P2")) { //if state is P2 then a new user is trying to signup.
				this.username = name; //empty username variable is assigned the value the user entered.
				Writer output; //initialized a variable with type output
				output = new BufferedWriter(new FileWriter("Users.txt", true)); //opens the file in append mode
				output.append(username + "\n"); // adds a new user to the next line of the textdocument.
				output.close();
			}
		} catch (IOException ioe) { 
			System.out.println("Error!");
			ioe.printStackTrace();
		}
		return ""; //returns an empty string if none of the requirements are met.
	}
}
