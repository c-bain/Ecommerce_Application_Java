/*Name: Corie Bain, Piranaven Selvathayabaran, Darsikan Anandarajah
MacID: bainc2, selvatp, anandad
Student Number: 001436514, 001419766, 001226159
Description: E-commerce Application | Done with Bonus #1 and #2 included.*/

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ShoppingCart extends User {
	private String content[]; //array of items
	private String currentPerson; //currentPerson String is initialized and set to private so it can't be directly changed from other classes.
	private String destinationTextFile; //This will become the text file of each users' cart.
	
	public ShoppingCart(String person){ //constructor
		this.currentPerson = person;  //person variable received by the constructor is stored in the currentPerson variable.
		this.destinationTextFile= "Cart"+"["+currentPerson+"]"+".txt"; //this represents the name of the shopping cart file of the user.
	}
	
	public String[] getContent(){ //This method returns the content of the shopping cart.
								  //Since the paper declared the content as an array it was assumed the getContent Method needed to return an array.			
		UserInterface retrieve = new UserInterface(); //creates an instance of UserInterface class
		String arrayCart[]= retrieve.buildArray(destinationTextFile); //builds an array of the shopping cart based on a specific user
		this.content = arrayCart; //the cart of each respective user is stored in the content variable
		return content;//returns the contents of the shopping cart
	}
	
	public void displayCart(){ //This method is used to display the cart
		UserInterface retrieve = new UserInterface(); //creates an instance of the class userInterface
		String arrayCart[]= retrieve.buildArray(destinationTextFile); //builds an array of the shopping cart based on a specific user
		this.content = arrayCart; //the cart of each respective user is stored in the content variable
		for (int i=0; i< content.length;i++)System.out.println(content[i]); //for loop is used to display the contents of the cart.
	}
	
	public void AddItem(String itemAdded, int sNo, int Quantity){ //Adds an item to the cart.
		DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY"); //These methods are used to get the date and time in the form DD/MM/YYYY.
		Calendar cal = Calendar.getInstance(); //creates an instance of the Calendar Class
		String dateAdded= dateFormat.format(cal.getTime()); //stores in dateAdded variable the time
		String newRecord = sNo+","+itemAdded+","+dateAdded+","+Quantity;  //concatenates the required line in the shopping cart
		UserInterface appendItem = new UserInterface(); //creates an instance of the UserInterface class
		appendItem.writeFile(newRecord,destinationTextFile); //New item is added to the Cart text file.
	}
}


