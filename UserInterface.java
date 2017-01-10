
/*Name: Corie Bain, Piranaven Selvathayabaran, Darsikan Anandarajah
MacID: bainc2, selvatp, anandad
Student Number: 001436514, 001419766, 001226159
Description: E-commerce Application | Done with Bonus #1 and #2 included.*/






import java.io.*;
import java.util.*;

public class UserInterface {
	private String readables[]; //contains an array of books.
	private String audioProducts[]; //contains and array of audioproducts.
	public String joint[]; //array which contains all the products joined together 
	public int position; //position to know our location when navigating through text files.
	private int currentPage; //currentPage variable is declared. This updates depending on which page we visit.
	public String adminpassword="ADMIN"; //Used to help implement bonus#2. This is the default password for the ADMIN
	public String previousOrders[]; //Used to help implement bonus#1. This array stores all previous orders made by the user.
	public int finalID=1000; //Is used to print the confirmation ID.
	ShoppingCart cart; //creates an instance of shopping Cart initially.
	
	
	public int getCurrentPage(int page) { //Assigns the variable currentPage a value depending on the page we're on.
		this.currentPage = page; //the private currentPage variable is assigned the page value argument.
		return currentPage;       //This method can also return the current page if needed.
	}
	
	public void cleanFiles(){ //rewrites the text files removing useless spaces and lines.
							 //creates a temporary file for manipulation and then deletes it after each file in the array files[] have been dealt with.
	createUsersFile(); //Used to initally create the Users.txt as well as put ADMIN on the first two lines. This is done to implement Bonus #2	
	String files[] = {"Books.txt","Ebooks.txt","MP3.txt","CDs.txt"}; //The array files[] is used in combination with the enhanced for loop to do a series of steps for each file.
	for (String file: files){ //enhanced for loop
		try {
			Scanner scanner = new Scanner(new File(file)); //reads from the current file in the array files[] when when writing to temp.txt
			PrintStream out = new PrintStream(new File("temp.txt")); //specifies which file we are writing to, which is temp.txt
			while(scanner.hasNextLine()){    //While a line exists
			    String line = scanner.nextLine(); //line is a string of the current line
			    line = line.trim(); //the line is trimmed to remove spaces
			    if(line.length() > 0) out.println(line); //only if there is content on the line, write that line into temp.txt
			}
			scanner.close(); //Scanner is closed
			out.close(); //Writer is closed
		}catch (FileNotFoundException fnfe) { //error handling for file not found.
			System.out.println("Unable to find file!");
		}
		String arrayTemp[] = buildArray("temp.txt"); //builds a temporary array with the lines of the temp file
		try {
			FileWriter fw = new FileWriter(file); //creates a variable fw of type FileWriter. The file that is being written to will be either books,ebooks,cds,mp3
			BufferedWriter bw = new BufferedWriter(fw); //creates a variable bw of type FileWriter
			for (int i=0;i<arrayTemp.length;i++){ //The file is populated with the contents of the temp file within this loop.
				bw.write(arrayTemp[i]); //writes the current string value of temp.
				bw.newLine(); //goes to the next line
			}
			bw.close(); //closes the buffered writer 
			fw.close(); //closes the fileWriter
		}catch (FileNotFoundException fnfe) { //error handling for file not found.
			System.out.println("Unable to find file!"); //Error Handling-Displays if error occurred.
		}
		catch(IOException ioe){ //Error handling for input/output exception
			System.out.println("Error!"); //Error Handling-Displays if error occurred.
		}
	}
	File here = new File("temp.txt"); //creates a variable here of type File
	String path=here.getAbsolutePath(); //gets the path of the file and stores it in variable path.
	File file = new File(path); //creates a variable file of type File.
	file.setWritable(true);//true so allows the write access permission //this is used to clean the temp file in preparation for deletion, becaue it cannot be deleted if it has contents.
	file.delete(); //deletes the temporary file
}

	public String checkDecision(Scanner decision){ //This method is used to check that the user is entering a number
		while(!decision.hasNextInt()) { //If the user doesn't enter a number the loop doesn't break.
		    decision.next(); //skips to next input
		    System.out.println("Not a valid option! Try again: "); //As long as user doesn't enter an int they are prompted.
		}
		String input = Integer.toString(decision.nextInt()); //if the loop breaks, that means a number was entered. It is then stored in the input variable.
		return input; //returns an acceptable input
	}
	
	public void getReadables() {   //This method creates an array from all the readables in the Books.txt file. Joins Books and eBooks
		String bookList[]=buildArray("Books.txt"); //Builds an array of all books
		String eBooksList[]=buildArray("Ebooks.txt");  //Builds an array of eBoooks
		List<String> readablesTemp = new ArrayList<String>(); //creates a temporary empty arraylist to put the contents of the books and eBooks. An arrayList was used to make appending easier.
		for (int i =0; i<bookList.length;i++){ //for loop, loops through bookList
			readablesTemp.add(bookList[i]);  //Adds all books to the temporary array.
		}
		for (int i =0; i<eBooksList.length;i++){ //Adds all ebooks to the temporary array.
			readablesTemp.add(eBooksList[i]); //adds all the ebooks to a temporary array
		}
		this.readables= new String[ readablesTemp.size() ]; //creates a new array readables of size readablesTemp 
		readablesTemp.toArray( readables ); //converts the arrayList back to an ordinary array.
		showReadables(readables); //sends the readables array to be printed.
	}
	

	public void getAudioProducts() { //This method creates an array from all the audiobooks in the Books.txt file.
		String cdList[]=buildArray("CDs.txt"); //Builds an array of all books
		String mp3List[]=buildArray("MP3.txt");  //Builds an array of eBoooks
		List<String> audioTemp = new ArrayList<String>(); //creates a temporary empty arraylist to put the contents of the books and eBooks. An arrayList was used to make appending easier.
		for (int i =0; i<cdList.length;i++){ //for loop, loops through cdList
			audioTemp.add(cdList[i]);  //Adds all cds to the temporary array.
		}
		for (int i =0; i<mp3List.length;i++){ //Adds all ebooks to the temporary array.
			audioTemp.add(mp3List[i]); //Adds all mp3s to the temporary array.
		}
		this.audioProducts = new String[ audioTemp.size() ]; //creates a new array audioProducts of size audioTemp 
		audioTemp.toArray( audioProducts ); //converts the arrayList back to an ordinary array.
		showAudioProducts(audioProducts); //sends the readables array to be printed.
	}
	
	public void join(){ //Method joins Books,Ebook,CDs, and MP3's
		String bookList[]=buildArray("Books.txt"); //Builds an array of all books
		String eBooksList[]=buildArray("Ebooks.txt");  //Builds an array of eBoooks
		String cdList[]=buildArray("CDs.txt"); //Builds an array of all books
		String mp3List[]=buildArray("MP3.txt");  //Builds an array of eBoooks
		List<String> allTemp = new ArrayList<String>(); //creates a tempor
		for (int i =0; i<bookList.length;i++){  //loops through bookList
			allTemp.add(bookList[i]);  //Adds all books to the temporary array.
		}
		for (int j =0; j<eBooksList.length;j++){ //loops through the eBooksList
			allTemp.add(eBooksList[j]); //Adds all ebooks to the temporary array.
		}
		
		for (int k =0; k<cdList.length;k++){ //loops through the cdList.
			allTemp.add(cdList[k]);  //Adds all cds to the temporary array.
		}
		for (int l =0; l<mp3List.length;l++){ //Adds all ebooks to the temporary array.
			allTemp.add(mp3List[l]); //Adds all mp3s to the temporary array.
		}
		this.joint = new String[ allTemp.size() ]; //creates a new array joint of size allTemp 
		allTemp.toArray( joint ); //converts the arraylist back to an array and stores in joint variable
		
	}
	
	public void showReadables(String array[]){//method to display the books to user as neatly as possible
		System.out.format("%5s%30s%10s%10s%10s%10s\n", "S.No", "Name", "Author", "Price($)", "Quantity", "Type");//printing formatting
		for (int i = 0; i < array.length; i++){ //loops through the array
			String temp[] = array[i].split(","); //creates a temp which splits the string by a comma so different parts can be accessed.
			String SNo=temp[0];String Name = temp[1]; String Author = temp[2]; String Price = temp[3]; String Quantity = temp[4]; String Type = temp[5]; //associates each part with a variable.
			System.out.format("%5s%30s%10s%10s%10s%10s\n", SNo,Name,Author,Price,Quantity,Type); //print formatting to display nicely to user.
		}
	}
	
	public void showAudioProducts(String array[]) { //method to display the books to user as neatly as possible
		System.out.format("%5s%30s%10s%10s%10s%10s\n", "S.No", "Name", "Artist", "Price($)", "Quantity", "Type"); //printing formatting
		for (int i = 0; i < array.length; i++){  //loops through the array
			String temp[] = array[i].split(","); //creates a temp which splits the string by a comma so different parts can be accessed.
			String SNo=temp[0];String Name = temp[1]; String Author = temp[2]; String Price = temp[3]; String Quantity = temp[4]; String Type = temp[5]; //associates each part with a variable.
			System.out.format("%5s%30s%10s%10s%10s%10s\n", SNo,Name,Author,Price,Quantity,Type); //print formatting to display nicely to user.
		}
	}
	
	public String snoExists(String option,String type){ //Method to determine if the Product the user chose exists.
		String array[]; //creates an empty array to be used locally in this method.
		if(type.equals("book")){array=readables;} //if the type is a book we are dealing with a selection from the user of type book and so readables is used.
		else {array=audioProducts;} //if not a book, we are dealing with a selection that is an audio product so audioProducts is used.
		boolean validSNo=true; // boolean variable validSNo initialized to true
		for (int i=0;i<array.length;i++){ //uses the readables array to loop through each line
			String temp[]=(array[i]).split(","); //At each line, the line is split to access the SNo number.
			String sNo = temp[0]; //The SNo number in the file is stored in the variable sNo
			if (sNo.equals(option)){ //if the SNo number in the file matches a number the user enters then
				validSNo = true;break;}  //the loop breaks and validSNo is assigned the value true.
			else validSNo = false;  //if the SNo number the user enters does not match any SNo numbers in the file validSNo is assigned false.
		}
		while(!validSNo){ //While validSNo is false, this means the user entered an invalid option and they are asked to enter a new option.
			System.out.println("Not valid!"+"\n"); //Display to user.
			System.out.println("--------------------------------------------------"); //Display to user.
			System.out.println("Choose your option: "); //Display to user.
			System.out.println("Enter -1 to exit "); //Display to user.
			Scanner userScanner1 = new Scanner(System.in); //creates a userScanner1 variable in preparation to get input from the user.
			option = checkDecision(userScanner1); //their new option is checked to make sure it is a number.
			for (int i=0;i<array.length;i++){ //the same thing is done as above using the same number.
				String temp[]=(array[i]).split(","); //creates a temp file and splits by the comma to access individual parts of the string 
				String sNo = temp[0]; //sNo is assigned the value of temp[0] which is the actual sNo in the string.
				if (option.equals("-1"))changeCurrentPage(6); //if the user enters -1 they will be redirected.
				else if (sNo.equals(option)){ //if the user's option equals a sNo on file then the item was found.
					validSNo = true;break;} //and validSNo is assigned true
				else validSNo = false; //if file not found validSNo is assigned false.
			}
		}
		return option; //the available selection is returned.
	}
	
	
	public boolean quantityCheckReadables (int option, int quantityNeeded){ //This method checks if readables are in stock
		boolean inStock; //initializes a boolean value
		Readable read = new Readable(); //creates an instance of Readable class
		String temp[]=(read.getInfo(option)).split(","); //gets info and splits into a temporary array.
		int quantity = Integer.parseInt(temp[4]); //quantity selected by user is checked with the quantity in stock
		if(quantity < quantityNeeded || quantity==0) { //if quantity is less then
			inStock =false; //inStock boolean value is false and returns
			System.out.println("\n"+"Only"+" "+quantity+" "+ "in stock"+"\n");//check if quantity needed by user is in stock
			System.out.println("Press -2 to continue shopping or Press 0 to Checkout"); //display to user
		}
		else inStock = true; //if quantity >= quantityNeeded then inStock returns as true.
		return inStock;
	}
	
	public boolean quantityCheckAudio(int option, int quantityNeeded){ //This method checks if audio is in stock. Does the same as quantityCheckReadables but is based on Audio.
		boolean inStock; //initializes a boolean value
		Audio listen = new Audio(); //creates an instance of the Audio class
		String temp[]=(listen.getInfo(option)).split(","); //creates a temp array and splits by the commas to access individual pieces of the string.
		int quantity = Integer.parseInt(temp[4]); //the quantity existed at temp[4], and is assigned to the variable quantity.
		if(quantity < quantityNeeded || quantity==0) { //if the quantity selected by the user is less than the quantity available in the text file
			inStock =false; //instock becomes false
			System.out.println("\n"+"Only"+" "+quantity+" "+ "in stock"+"\n");//check if quantity needed by user is in stock
			System.out.println("Press -2 to continue shopping or Press 0 to Checkout"); //display to user
		}
		else inStock = true; //if quantity available then inStock is assigned true
		return inStock; //returns the boolean value inStock
	}
	
	public void updateQuantity(int selection,int newQuantity,String type){ //This method updates the new quantity in the respective text file.
		if (type.equals("Book")||type.equals("Book ")){ //checks the type to enure the right file is written to.
			Readable read = new Readable();
			String temp[]=(read.getInfo(selection)).split(","); //creates a temporary array to access the individual elements
			String SNo = temp[0]; //SNo can be found at location 0.
			int position =0; //This variable is initialized in preparation of finding the user's selection in the original file.
			//SNo is used to loop through the original file and stop when it is found, then rewrite that line
			temp[4]=Integer.toString(newQuantity); //replaces the old quantity with the new.
			String result = ""; //prepares an empty result in preparation for concatenation.
			for (int i=0; i < temp.length; i++){
				result = result + temp[i]; //the new line is built
				result =result + ","; //result is built separated by commas.
			}
			result =result.substring(0,result.length()-1)+""; //removes last comma
			String array[]=buildArray("Books.txt"); //Builds an array from the books file.
			for (int i=0;i<array.length;i++){ //loop is used to locate the selection of the user, when correct SNo is found, the loop breaks.
				String current=array[i]; //populates the array
				String line[]= current.split(","); 
				String booksSNo = line[0]; //SNo can be found at location 0.
				if (booksSNo.equals(SNo))break; //if the SNo in the file equals the SNo of the user, the loop breaks
				position+=1; //the current position is updated
			}
			array[position]=result; //when loop breaks and position is found, we overwrite that line with the result we built above.
			try {
				FileWriter fileOut = new FileWriter("Books.txt"); //the above array is now used to write to the actual file.
				fileOut.write(""); //Empties the file 
				for(int i=0;i<array.length;i++) fileOut.append(array[i]+"\n"); //rewrites the updated quantity and other things.
				fileOut.close();//fileOut is closed.
			} catch (IOException ioe) { //Input/output Error Handling.
			ioe.printStackTrace();
			}
		}
		else if (type.equals("eBook")||type.equals("eBook ")){ //Same Process as Books, above, only difference is, eBooks is handled here.
			Readable read = new Readable(); //creates a new instance of the class Readable
			String temp[]=(read.getInfo(selection)).split(","); //creates a temp array and splits by the commas to access individual elements of the string.
			String SNo = temp[0]; //temp[0] is the sNo value in the string and SNo is assigned its value.
			int position =0; //position is initialized at 0.
			//use the SNo to loop through the original file and stop when it is found, then rewrite that line
			temp[4]=Integer.toString(newQuantity); //replaces the old quantity with the new.
			String result = ""; //prepares an empty result in preparation for concatenation.
			for (int i=0; i < temp.length; i++){ //loops through the temp array
				result = result + temp[i]; //builds the result starting from an empty string.
				result =result + ","; //after each element, a comma is inserted.
			}
			result =result.substring(0,result.length()-1)+""; //removes last comma
			String array[]=buildArray("Ebooks.txt"); //converts the Ebooks.txt file into an array
			for (int i=0;i<array.length;i++){ //loops through the Ebooks array.
				String current=array[i]; //assigned the current line to the variable current
				String line[]= current.split(","); //splits the current line so elements can be easier accessed.
				String booksSNo = line[0]; //line[0] has the SNo value, we store that into the variable booksSNo
				if (booksSNo.equals(SNo))break; //if booksSNo value equals the SNo the user entered we stop the loop and record the position reached.
				position+=1; //position is incremented each time.
			}
			array[position]=result; //now that the position of the SNo the user entered is known, we know which line must be replaced
			try {
				FileWriter fileOut = new FileWriter("Ebooks.txt"); //we open are file we are writing to
				fileOut.write(""); //Empties the file 
				for(int i=0;i<array.length;i++) fileOut.append(array[i]+"\n"); //rewrites the updated quantity and other things.
				fileOut.close(); //closes fileOut
			} catch (IOException ioe) { //Input/output Error Handling.
			ioe.printStackTrace();
			}
		}
		//SAME PROCESS AS BOOKS AND EBOOKS ABOVE, ONLY DIFFERENCE IS, CD AND MP3 IS NOW BEING DEALT WITH.
		else if (type.equals("CD")||type.equals("CD ")){ //Same Process as Books, above, only difference is, CD is handled here.
			Audio listen = new Audio();
			String temp[]=(listen.getInfo(selection)).split(",");
			String SNo = temp[0];
			int position =0;
			//use the SNo to loop through the original file and stop when it is found, then rewrite that line
			temp[4]=Integer.toString(newQuantity); //replaces the old quantity with the new.
			String result = ""; //prepares an empty result in preparation for concatenation.
			for (int i=0; i < temp.length; i++){
				result = result + temp[i];
				result =result + ",";
			}
			result =result.substring(0,result.length()-1)+""; //removes last comma
			String array[]=buildArray("CDs.txt");
			for (int i=0;i<array.length;i++){
				String current=array[i];
				String line[]= current.split(",");
				String CDsSNo = line[0];
				if (CDsSNo.equals(SNo))break;
				position+=1;
			}
			array[position]=result;
			try {
				FileWriter fileOut = new FileWriter("CDs.txt");
				fileOut.write(""); //Empties the file 
				for(int i=0;i<array.length;i++) fileOut.append(array[i]+"\n"); //rewrites the updated quantity and other things.
				fileOut.close();
			} catch (IOException ioe) { //Input/output Error Handling.
			ioe.printStackTrace();
			}
		}
		
		else if (type.equals("MP3")||type.equals("MP3 ")){ //Same Process as Books, above, only difference is, MP3 is handled here.
			Audio listen = new Audio();
			String temp[]=(listen.getInfo(selection)).split(",");
			String SNo = temp[0];
			int position =0;
			//use the SNo to loop through the original file and stop when it is found, then rewrite that line
			temp[4]=Integer.toString(newQuantity); //replaces the old quantity with the new.
			String result = ""; //prepares an empty result in preparation for concatenation.
			for (int i=0; i < temp.length; i++){
				result = result + temp[i];
				result =result + ",";
			}
			result =result.substring(0,result.length()-1)+""; //removes last comma
			String array[]=buildArray("MP3.txt");
			for (int i=0;i<array.length;i++){
				String current=array[i];
				String line[]= current.split(",");
				String mp3SNo = line[0];
				if (mp3SNo.equals(SNo))break;
				position+=1;
			}
			array[position]=result;
			try {
				FileWriter fileOut = new FileWriter("MP3.txt");
				fileOut.write(""); //Empties the file 
				for(int i=0;i<array.length;i++) fileOut.append(array[i]+"\n"); //rewrites the updated quantity and other things.
				fileOut.close();
			} catch (IOException ioe) { //Input/output Error Handling.
			ioe.printStackTrace();
			}
		}
	}
	
	public int checkInput(String userinput){ //This method is used to check input for shopping cart confirmation.
		userinput = userinput.toLowerCase(); //converts the string to lowercase
		int answer;  //creates variable answer
		if (userinput.equals("yes"))answer = 1; //checks if the answer is yes and assigns a variable answer to 1 
		else if(userinput.equals("no"))answer = 2; //checks if the answer is no and assigns a variable answer to 2 
		else answer = 3; //else answer is assigned the variable 3
		return answer; //variable answer is returned.
	}
	
	public void changeCurrentPage(int page) { //This method is used to change pages.
		if (page == 1) P1(); //if page variable is 1, goes to P1();
		else if (page == 2) P2(); //if page variable is 2, goes to P2();
		else if (page == 3) P3(); //if page variable is 3, goes to P3();
		else if (page == 5) P5(); //if page variable is 4, goes to P4();
		else if (page == 4)P4(); //if page variable is 5, goes to P5();
		else if (page == 6) P6(); //if page variable is 6, goes to P6();
		else if (page == 7) P7(); //if page variable is 7, goes to P7();
	    else if (page == 8) P8(); //if page variable is 8, goes to P8();
		else if (page == 9) P9(); //if page variable is 9, goes to P9();
		else if (page == 10)P10(); //if page variable is 10, goes to P10();
	}

	public int arraySize(String filetoRead) { // This Method returns the size of a textfile
		int count =1; //counter initialized
		try {
			FileReader fileread = new FileReader(filetoRead); //creates a FileReader object
			BufferedReader buffread = new BufferedReader(fileread); //creates a BufferedReader object.
			String readString = buffread.readLine(); //stores the first line in the readString variable.
			while (readString != null) // Checks for the end of the file.
			{
			count+=1; //count variable is incremented.
			readString = buffread.readLine(); //At each line the contents of the line are stored in the variable readString.
			}
			buffread.close(); //BufferedReader is closed.
		}catch (FileNotFoundException fnfe) { //error handling for file not found.
			System.out.println("Unable to find file!"); //display to user if error occurs.
		}catch (IOException ioe) { //error handling for input/output.
			ioe.printStackTrace();
		}
		return count; //returns count, which is the size of the textfile.
		}
	
	public void readFile(String filetoRead) { // Method that prints the contents of a text file.
		try {
			FileReader fileread = new FileReader(filetoRead); //creates a FileReader object
			BufferedReader buffread = new BufferedReader(fileread); //creates a BufferedReader object.
			String readString = buffread.readLine(); //stores the first line in the readString variable.
			while (readString != null) // Checks for the end of the file.
			{
				System.out.println(readString); //Each line is printed.
				readString = buffread.readLine(); //At each line the contents of the line are stored in the variable readString.
			}
			buffread.close(); //BufferedReader is closed.
		} catch (FileNotFoundException fnfe) { //error handling for file not found.
			System.out.println("Unable to find file!"); //displays to user.
		} catch (IOException ioe) { //error handling for input/output.
			ioe.printStackTrace();
		}
	}
	
	public void createUsersFile(){
		try{
			Writer output; //initialized a variable with type output
			output = new BufferedWriter(new FileWriter("Users.txt", true)); //creates and opens new file users.txt in append mode
			output.append("ADMIN" + "\n"); // adds text "ADMIN" to the line
			output.append("ADMIN" + "\n"); // adds text "ADMIN" to the line
			output.close(); //closes output
		} catch (IOException ioe) {  //Input/Output Error handling
			System.out.println("Error!");
			ioe.printStackTrace();
		}	
	}
	
	
	public void writeFile(String toAppend,String toFile) { // Method that writes content to a file.
	try {
		Writer Output; //initializes Output variable of type Writer.
		Output = new BufferedWriter(new FileWriter(toFile, true)); //Opens the file in append mode  
		Output.append(toAppend + "\n"); // adds a new line to the next line of the text document.
		Output.close(); //Output is closed.
	} catch (FileNotFoundException fnfe) { //File not found Error Handling
	System.out.println("Unable to find file!");  //display to user
	} catch (IOException ioe) { //Input/output Error Handling.
	ioe.printStackTrace();
	}
}
	public String[] buildArray(String filetoRead) { //This method builds an array given a text file.
		int size=arraySize(filetoRead); //To know the size of the array, the method arraySize is used to find the number of lines in a file and hence the array size.
		String array[] = new String[size-1]; //the returned size value is used for the array size, with an offset of 1.
		try {
			FileReader fileread = new FileReader(filetoRead); //creates a variable fileread of type FileReader
			BufferedReader buffread = new BufferedReader(fileread); //creates a variable buffread of type BufferedReader.
			String readString = buffread.readLine(); //first line stored in readString variable
			int index = 0; // Used within While as a counter
			while (readString != null) // test for the end of the file
			{
				array[index] = readString; //array[index] is assigned the content on the line of the textfile.
				readString = buffread.readLine(); //nextline
				index += 1; //array[index+1] 
			}
			buffread.close(); //BufferredReader closes
		} catch (FileNotFoundException fnfe) { //File Not Found Error Handling
			System.out.println("Unable to find file!");
		} catch (IOException ioe) { //Input/Output Error Handling
			ioe.printStackTrace();
		}
		return array; //returns the array
	}

	public void P1() { //This is the Login/Signup Page
		getCurrentPage(1); // Stores the current page in the variable currentPage.
		System.out.println("1.Sign in"); //display to user
		System.out.println("2.Sign up"); //display to user
		System.out.println("Choose your option: "); //display to user
		Scanner userScanner1 = new Scanner(System.in); //creates a variable 
		String selection1 = checkDecision(userScanner1);
		if (Integer.parseInt(selection1) == 1) changeCurrentPage(3); // If the user chooses 1 we navigate them to page 3.
		else if (Integer.parseInt(selection1) == 2) changeCurrentPage(2);
		else {System.out.println("Not a valid option!\n");changeCurrentPage(1);} //incase the user enters numbers not listed.
		userScanner1.close(); //No more input to receive so the Scanner is closed.
	}
	
	public void P2() { //This method is used to Signup a new user.
		getCurrentPage(2);
		User userName = new User();
		userName.State = "P2"; // State Variable is used in commbination with the User class to SHIFT between two flows of the program. One being an existing user and the other being a new user.
							   //State is changed to P2 so the new user signup block can be ran in the user class.
		System.out.println("Choose your username: ");
		Scanner userScanner1 = new Scanner(System.in);
		String selection1 = userScanner1.nextLine(); //The user's choice of username is stored in the selection1 variable.
		userName.getUsername(selection1);//The getUsername method in the userclass creates the new user.
		System.out.println("Username successfully added");
		try { //actually adds a cart file for the user.
			PrintWriter writer;
			writer = new PrintWriter("Cart"+"["+selection1+"]"+".txt","UTF-8"); //Creates a new txt file for the user's cart.
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		changeCurrentPage(1); //If the user was successfully created, the user is taken back to the login/signup page.
		userScanner1.close();
	}

	public void P3() { 
		getCurrentPage(3);
		System.out.println("Enter your username: ");
		Scanner userScanner = new Scanner(System.in);
		String userinput = userScanner.nextLine();
		if (userinput.equals("ADMIN")){
			admin();
		}
		User currentUser = new User();
		String userName = currentUser.getUsername(userinput); //username entered is passed to the getUsername method to check if the username is on file.
		if (userName!=""){
			System.out.println("\n"+"Hello Mr." + userName + "\n"); //if the username successfully matches a name on file then it is returned and printed.	
			cart = new ShoppingCart(userName); //the name is passed to the Shopping Cart class as soon as the user is able to login.
			try{
				FileReader fileread = new FileReader("Users.txt");
				BufferedReader buffread = new BufferedReader(fileread);
				String readString=""; 
				position=0; //is used along with the While loop below to find the position of the user in the users text file for later use.
				while (readString != null) // test for the end of the file
				{
					position+=1; //increments the position number when moving through lines.
					readString = buffread.readLine(); //goes through the text file line by line
					if (userName.equals(readString)) { //if the username entered matches a username on file
						readString = buffread.readLine();
						break; //the loop breaks when the user is found
					}
				}
				buffread.close();
			} catch (IOException ioe) { 
				System.out.println("Error!");
				ioe.printStackTrace();
			}
			changeCurrentPage(5); //Goes to P5.
		}
	userScanner.close();	
	}
	public void P4(){
		getCurrentPage(4);
		System.out.print("No Access!\n"); // If no username found then no access.
		UserInterface goback = new UserInterface(); 
		goback.changeCurrentPage(1); // Redirects user back to Page1
	}

	public void P5() {
		getCurrentPage(5);
		System.out.println("--------------------------------------------------");
		System.out.println("1.View Items By Category"); //Options presented to user.
		System.out.println("2.View Shopping Cart");
		System.out.println("3.Sign Out");
		System.out.println("4.View Previous Orders\n");
		System.out.print("Choose your option: ");
		Scanner userScanner = new Scanner(System.in);
		String selection = checkDecision(userScanner);
		String findPerson[]=buildArray("Users.txt");
		if(Integer.parseInt(selection) == 4 )buildPreviousOrders(findPerson[position-1]);
		else if (Integer.parseInt(selection) == 3) //Redirects based on the options chosen by the user.
			changeCurrentPage(1);   
		else if (Integer.parseInt(selection) == 2) changeCurrentPage(7);//Redirects based on the options chosen by the user.
		else if (Integer.parseInt(selection) == 1) changeCurrentPage(6);
		else {System.out.println("Not a valid option!\n");changeCurrentPage(5);} //incase the user enters numbers not listed.
		userScanner.close();
	}

	public void P6() { //This method displays the Readables and Audio to the user and requires them to make a selection.
		getCurrentPage(6);
		System.out.println("--------------------------------------------------");
		System.out.println("1.Readables");
		System.out.println("2.Audio"+"\n");
		System.out.println("Choose your option: ");
		System.out.println("Press -1 to return to the previous menu");
		Scanner userScanner = new Scanner(System.in);
		String selection = checkDecision(userScanner);
		if (Integer.parseInt(selection) == -1) //User decides to return to previous menu
			changeCurrentPage(currentPage-1);
		else if (Integer.parseInt(selection) == 1) changeCurrentPage(8); // User requests to see readables.
		else if (Integer.parseInt(selection) == 2)changeCurrentPage(9);  //User requests to see Audio
		else {System.out.println("Not a valid option!\n");changeCurrentPage(6); userScanner.close();} //incase the user enters numbers not listed.
		userScanner.close();
	}
	
	public void P7() { //This method displays the shopping Cart. 
		getCurrentPage(7);
		cart.displayCart();
		System.out.println("\n"+"Press -1 to return to the previous menu: ");
		Scanner userScanner = new Scanner(System.in);
		String selection = checkDecision(userScanner); //checkDecision method is used to make sure a suitable input is entered.
		if (Integer.parseInt(selection) == -1)changeCurrentPage(currentPage-1);  //Redirects based on the options chosen by the user.
		else {System.out.println("Not a valid option!\n");changeCurrentPage(7); userScanner.close();} //incase the user enters numbers not listed.  
	}
	

	public void P8() {   //User requested to see Readables
		getCurrentPage(8);
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Readables: " + "\n");
		getReadables(); //Displays to the user
		System.out.println("\n" + "Choose your option: " + "\n");
		System.out.println("Press -1 to return to previous menu.");
		Scanner userScanner1 = new Scanner(System.in);
		String selection1 = checkDecision(userScanner1); //checkDecision method is used to make sure a suitable input is entered.
		if (Integer.parseInt(selection1) == -1)changeCurrentPage(currentPage-3); //Previous Menu
		selection1=snoExists(selection1,"book"); //checks if the item the user requested exists.
		if (Integer.parseInt(selection1) == -1)changeCurrentPage(currentPage-3); //Previous Menu
		else { // Only one path exists so no need for another if.
			System.out.println("--------------------------------------------------");
			System.out.println("Choose your option: "+ selection1); //Shows the user their current selection.
			System.out.print("Enter Quantity: "+"\n");
			Scanner userScanner3 = new Scanner(System.in);
			String selection2 = checkDecision(userScanner3); //checkDecision method is used to make sure a suitable input is entered.
			int quantityAdded = Integer.parseInt(selection2);//The user's quantity is stored in quantityAdded.
			Readable read = new Readable();
			String temp[]=(read.getInfo(Integer.parseInt(selection1))).split(",");
			boolean inStock = quantityCheckReadables((Integer.parseInt(selection1)),quantityAdded);
			while (inStock==false){
				System.out.println("--------------------------------------------------");
				System.out.print("Enter Quantity: "+"\n");
				userScanner3 = new Scanner(System.in);
				String selection3 = checkDecision(userScanner3); //checkDecision method is used to make sure a suitable input is entered.
				quantityAdded = Integer.parseInt(selection3);//The user's quantity is stored in quantityAdded.
				inStock = quantityCheckReadables((Integer.parseInt(selection1)),quantityAdded);
				if (Integer.parseInt(selection3)==-2)changeCurrentPage(6); //Fix this, user should be able to exit loop at will
			}
			int sNo = Integer.parseInt(temp[0]);
			String type =temp[5];
			String item = temp[1]; //The 1 index contains the name of the book.
			System.out.println(quantityAdded+" "+item+" "+ "was successfully added to your cart."+"\n");
			cart.AddItem(item,sNo,quantityAdded); //The item is added to the Shopping Cart
			int newQuantity=Integer.parseInt(temp[4])-quantityAdded;
			updateQuantity(Integer.parseInt(selection1),newQuantity,type); //Updates quantity in corresponding text file.
			System.out.println("--------------------------------------------------");
			System.out.println("Press -2 to continue shopping or Press 0 to Checkout");
			Scanner userScanner4 = new Scanner(System.in); 
			String selection4 = checkDecision(userScanner4); //checkDecision method is used to make sure a suitable input is entered.
			if (Integer.parseInt(selection4)==-2)changeCurrentPage(6); //continue shopping from P6()
			else if (Integer.parseInt(selection4) == 0)changeCurrentPage(10); //or checkout
			else {System.out.println("Not a valid option!\n");changeCurrentPage(8);} //incase the user enters numbers not listed.
			userScanner1.close();
			userScanner3.close();
			userScanner4.close();
		}
	}
	
	public void P9() {   //User requested to see Readables
		getCurrentPage(9);
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Audio: " + "\n");
		getAudioProducts(); //Displays to the user
		System.out.println("--------------------------------------------------");
		System.out.println("\n" + "Choose your option: " + "\n");
		System.out.println("Press -1 to return to previous menu.");
		Scanner userScanner1 = new Scanner(System.in);
		String selection1 = checkDecision(userScanner1);//checkDecision method is used to make sure a suitable input is entered.
		if (Integer.parseInt(selection1) == -1)changeCurrentPage(currentPage-4); //Previous Menu
		selection1=snoExists(selection1,"audio"); //checks if the item the user requested exists.
		if (Integer.parseInt(selection1) == -1)changeCurrentPage(currentPage-4); //Previous Menu
		else { // Only one path exists so no need for another if.
			System.out.println("--------------------------------------------------");
			System.out.println("Choose your option: "+ selection1); //Shows the user their current selection.
			System.out.print("Enter Quantity: "+"\n");
			Scanner userScanner3 = new Scanner(System.in);
			String selection3 = checkDecision(userScanner3);//checkDecision method is used to make sure a suitable input is entered.
			int quantityAdded = Integer.parseInt(selection3);//The user's quantity is stored in quantityAdded.
			Audio listen = new Audio();
			String temp[]=(listen.getInfo(Integer.parseInt(selection1))).split(",");
			boolean inStock = quantityCheckAudio((Integer.parseInt(selection1)),quantityAdded);
			while (inStock==false){
				System.out.println("--------------------------------------------------");
				System.out.print("Enter Quantity: "+"\n");
				selection3 = checkDecision(userScanner3);//checkDecision method is used to make sure a suitable input is entered.
				quantityAdded = Integer.parseInt(selection3);//The user's quantity is stored in quantityAdded.
				inStock = quantityCheckAudio((Integer.parseInt(selection1)),quantityAdded); //method quantityCheckAudio checks if the quantity the user requested is in stock
				if (Integer.parseInt(selection3)==-2)changeCurrentPage(6); //Fix this, user should be able to exit loop at will
			}
			int sNo = Integer.parseInt(temp[0]);
			String type =temp[5];
			String item = temp[1]; //The 1 index contains the name of the book.
			System.out.println(quantityAdded+" "+item+" "+ "was successfully added to your cart."+"\n");
			cart.AddItem(item,sNo,quantityAdded); //The item is added to the Shopping Cart
			int newQuantity=Integer.parseInt(temp[4])-quantityAdded;
			updateQuantity(Integer.parseInt(selection1),newQuantity,type); //Updates quantity in corresponding text file.
			System.out.println("--------------------------------------------------");
			System.out.println("Press -2 to continue shopping or Press 0 to Checkout");
			Scanner userScanner4 = new Scanner(System.in); 
			String selection4 = checkDecision(userScanner4); //checkDecision method is used to make sure a suitable input is entered.
			if (Integer.parseInt(selection4)==-2)changeCurrentPage(6); //continue shopping from P6()
			else if (Integer.parseInt(selection4) == 0)changeCurrentPage(10); //or checkout
			else {System.out.println("Not a valid option!\n");changeCurrentPage(9);} //incase the user enters numbers not listed.
			userScanner1.close(); //userScanners are closed.
			userScanner3.close();
			userScanner4.close();
		}
	}
	
public void P10() {
		getCurrentPage(10);
		String purchases[]=cart.getContent(); // calls specific shopping cart
		int HST = 0; //initialize hst 
		int shipping = 0; 
		int  total= 0; 
		int etax = 0 ; 
		int counter = 0;
		String ConfirmationID= "U";
		//int ConfirmationNumber= 1000; 
		for (int i = 0; i < purchases.length; i ++){ // this is a for loop, that counts how many elements are in he array
		    if (purchases[i] != null)
		        counter ++;
		}
	String [][] array2d = new String[counter][5]; // creates a 2d arrray of length of counter, and width  5 
		for(int i=0 ; i<purchases.length; i++){
			String tempArray[] = purchases[i].split(","); // using for loop, takes the first element(which is a string ), splits it into a temp array
			for (int k= 0; k < array2d.length; k++) {
			    for (int j = 0; j < array2d[0].length; j++) {
			    	if(j == 2){									//these 2 for loops, adds the SNO, title and quantity into the 2d array via the temp array
			    		array2d[i][j]= tempArray[j+1];
			    		j= j+2;		
			    	}
			    	else{
			    		array2d[i][j] = tempArray[j];
			    	}
			    }
		}
	}
		Readable readInfo = new Readable(); // create Readable instance. 
		Audio audioInfo = new Audio();
		for (int k= 0; k < array2d.length; k++) {
			readInfo.price =0; 
			readInfo.getInfo(Integer.parseInt(array2d[k][0])); // using for loop, sends all SNO, one by one , to get matching inventory line and sets global variables in item
			if (readInfo.price == 0){
				audioInfo.getInfo(Integer.parseInt(array2d[k][0]));
				array2d[k][3] = Integer.toString(audioInfo.price);  // at this instance calls variable from item class  and adds it 2d arrray
				array2d[k][4] = audioInfo.type;  // at this instance calls variable in item class and adds it 2d arrray
				HST= (int) (HST+ (Integer.parseInt(array2d[k][2])*Integer.parseInt(array2d[k][3])*0.13)); // then calculates the hst for the item and adds to HST variable
			}
			else{
				array2d[k][3] = Integer.toString(readInfo.price);  // at this instance calls variable from item class  and adds it 2d arrray
				array2d[k][4] = readInfo.type;  // at this instance calls variable in item class and adds it 2d arrray
				HST= (int) (HST+ (Integer.parseInt(array2d[k][2])*Integer.parseInt(array2d[k][3])*0.13)); // then calculates the hst for the item and adds to HST variable
				//System.out.println(array2d[k][4]);
			}
		}
		for (int l= 0; l < array2d.length; l++) {
		    for (int j = 0; j < array2d[0].length; j++) {
		    	//System.out.println(array2d[l][j]);  
		    }
		    }
		for (int k= 0; k < array2d.length; k++) { // for loop through every item in the 2dArray 
			String temp = array2d[k][4];  // assigns the type of every item to temp 
			//System.out.println(array2d[k][4]);
			if(temp.equals("Book") || temp.equals("CD") ){ // checks if the type is equal to book or cd
				int tempprice= Integer.parseInt(array2d[k][3]);  // if yes, assigns the corresponding price to tempprice 
				int tempquantity= Integer.parseInt(array2d[k][2]);//if yes, assigns the corresponding price to tempquantity 
				shipping = (int) (shipping + (tempprice * tempquantity * 0.10)); // calculates and accumulates shipping 
			}
		}
		for (int p= 0; p < array2d.length; p++) { // for looop through every item in shoping cary array 
			String tempo = array2d[p][4]; // assign the items type to tempo 
			int SNO = Integer.parseInt(array2d[p][0]); // assigns its sno  
			int Quantity = Integer.parseInt(array2d[p][2]); // assigns the amount wanted of the item 
			int basep = Integer.parseInt(array2d[p][3]);  // assigns the base price of the wanted item 
			if(tempo.equals("Book")) { 
				Book bprice = new Book();
				double bookp = bprice.getPrice(SNO,Quantity ); //calls books price method which overrides and returns the price with shipping included
				etax = (int) (etax + ( bookp -(basep*Quantity ))); // from the last lines return, isolates the environment tax and assigns to etax
				total= (int) (total + (bookp)); // sums the total witth the price of the book with its tax included  
				
			}
			else if(tempo.equals("CD")) {
				CD cdprice = new CD();
				double CDp = cdprice.getPrice(SNO,Quantity);
				etax = (int) (etax + ( CDp -(basep*Quantity ))); /// does the exact same thing as last if statemnt  but with CD
				total= (int) (total + (CDp)); 
			}
			else if(tempo.equals("MP3")) {
				MP3 mp3price = new MP3();
				double MP3p = mp3price.getPrice(SNO,Quantity);  // calls getprice method thats inherited from  item class, returns the price given quanitty and selection 
				total= (int) (total + (MP3p));  // adds to total 
			}
			else if(tempo.equals("eBook")) {
				eBook eBookprice = new eBook();  // does the same thing as last else if statement; 
				double ebp = eBookprice.getPrice(SNO,Quantity);
				total= (int) (total + (ebp)); 
				
			}
		}
		total = (int) (total + HST + shipping); // adds total with  HST and Shipping 
		System.out.println("--------------------------------------------------");
		System.out.println("Billing Information: "+"\n");
		System.out.format("%10s%10s%10s\n","Name","\t\t\tQuantity","\t\tPrice"); //String formatting is used to generate a table effect.
		ArrayList<String> products = new ArrayList<String>();
		for (int l= 0; l < array2d.length; l++) { //String formatting is used to generate a table effect.
		    for (int j = 0; j < array2d[0].length; j++) {
		    	System.out.format("%10s%10s%10s\n",array2d[l][j+1],"\t\t"+array2d[l][j+2],"\t\t\t"+array2d[l][j+3]); //String formatting is used to generate a table effect.
		    	j=j+5;   
		    }
		}
		System.out.format("%10s%11s%10s\n","\nEnvironment Tax","2%","\t\t\t\t"+etax); //String formatting is used to generate a table effect.
		System.out.format("%10s%10s%10s\n","HST","\t13%","\t\t\t\t"+HST);System.out.println(); //String formatting is used to generate a table effect.
		System.out.format("%10s%10s%10s\n","Shipping","\t\t10%","\t\t\t\t"+shipping); //String formatting is used to generate a table effect.
		System.out.println("                                                      ___________________ ");
		System.out.format("%10s%10s\n","Total","\t\t\t\t\t\t"+total+"$"+"\n"); //String formatting is used to generate a table effect.
		System.out.println("Are you sure you want to pay? Yes or No?");
		
		Scanner userScanner1 = new Scanner(System.in); 
		String selection1 = userScanner1.nextLine(); //selection1 variable stores the decision of the user.
		int selection2 = checkInput(selection1); 
		if (selection2 ==1){ //The code below is used to implement Bonus #1
			File itemsBought = new File("ItemsBought.txt"); //stores the ItemsBought text file in a variable. 
			String productList = products.toString();
			String findPerson[]=buildArray("Users.txt");  //builds an array findPerson with the Users.txt file
			try{
			    if(!itemsBought.exists()){ //if ItemsBought.txt does not exist
			    	itemsBought.createNewFile(); //then create a new file
				    FileWriter fileWriter = new FileWriter(itemsBought, true); //opens for appending
				    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); //opens for writing
				    String content[]=cart.getContent(); //get the content of the current selection
				    String temp[] = content[0].split(","); //creates a temp array for easier access of the elements on the line
				    String item = temp[1]; //item is at position 1
				    String result = "" + findPerson[position - 1]+ ","+ item+ "," + ConfirmationID + finalID + ","+ total + "," + productList.substring(1, productList.length()-1) +  "\n"; //the necessary string is concatenated.
				    result =result.substring(0,result.length()-2)+""; //removes last comma
				    bufferedWriter.write(result+"\n"); //writes the string to the file and inserts a newline character.
				    bufferedWriter.close();//closes the bufferedWriter.
				    System.out.println("Confirmation ID: "+ConfirmationID + finalID ); //prints the confirmation ID
					System.out.println("Items Shipped to : Mr."+findPerson[position-1]); //display to user
					String deleteCart=findPerson[position - 1]; //deleteCart stores the current user.
					File here = new File("Cart"+"["+deleteCart+"]"+".txt"); //creates a variable here of type File
					String path=here.getAbsolutePath(); //gets the path of the file and stores it in variable path.
					File file = new File(path); //creates a variable file of type File.
					file.setWritable(true);//true so allows the write access permission //this is used to clean the cart file in preparation for deletion, because it cannot be deleted if it has contents.
					file.delete(); //deletes the user's cart
					
			    }
			    else{
			    	Writer output; //initialized a variable with type output
					output = new BufferedWriter(new FileWriter("ItemsBought.txt", true)); //as long as the writing isn't over
					String content[]=buildArray("ItemsBought.txt"); //builds an array content with the text file ItemsBought
					int size=arraySize("ItemsBought.txt"); //stores the size of that array in size
				    String temp[] = content[size-2].split(","); //this is where the previous line is located. We need this to increment the confirmation ID
				    String ID = temp[2];ID=ID.substring(1,5); //we only need the string starting from 1 to 5 to get the numerical value for addition.
				    int newID= Integer.parseInt(ID)+1; //the newID is calculated by incrementing 1
				    this.finalID = newID; //the newID value is stored in the finalID variable
				    String getItem[]=buildArray("Cart"+"["+findPerson[position - 1]+"]"+".txt"); //This is done in preparation to get the user's selection.
				    int size2=arraySize("Cart"+"["+findPerson[position - 1]+"]"+".txt"); //Inside the user's cart is searched to get the last thing added.
				    String temp2[] = getItem[size2-2].split(","); //temp variable is created to access elements easier.
				    String item = temp2[1];//temp2[1] contained the result of the item.
				    String result = "" + findPerson[position - 1]+ ","+ item+ "," + ConfirmationID+finalID + ","+ total + "," + productList.substring(1, productList.length()-1) +  "\n";
				    result =result.substring(0,result.length()-2)+""; //removes last comma
					output.append(result + "\n"); // adds a new user to the next line of the textdocument.
					output.close(); //output is closed.
					String deleteCart=findPerson[position - 1]; //stores current user in deleteCart
					System.out.println("Confirmation ID: "+ConfirmationID + finalID ); //display to user 
					System.out.println("Items Shipped to : Mr."+findPerson[position-1]); //display to user
					File here = new File("Cart"+"["+deleteCart+"]"+".txt"); //creates a variable here of type File
					String path=here.getAbsolutePath(); //gets the path of the file and stores it in variable path.
					File file = new File(path); //creates a variable file of type File.
					file.setWritable(true);//true so allows the write access permission //this is used to clean the temp file in preparation for deletion, becaue it cannot be deleted if it has contents.
					file.delete(); //deletes the temporary file
					
			    }
			} catch(IOException e) {
			}
		}
		
		else if (selection2 == 2) { // User requests to see readables.
			changeCurrentPage(6);
		} else
			changeCurrentPage(1); // User requests to see Audio
		System.out.println("--------------------------------------------------");
		System.out.println("Thank you for shopping with us\n");
		System.out.println("1.Sign out"); //Options presented to user
		System.out.println("2.Continue shopping\n");
		System.out.print("Choose your option: ");
		Scanner userScannerLast = new Scanner(System.in);
		String selection = checkDecision(userScannerLast);
		System.out.println("--------------------------------------------------");
		
		if (Integer.parseInt(selection) == 2) //Redirects based on the options chosen by the user.
			changeCurrentPage(6);
		else changeCurrentPage(1);
		userScanner1.close();userScannerLast.close();
		
}

public void buildPreviousOrders(String person){ //Method is used to View previous orders.
	String orders[] = buildArray("ItemsBought.txt");
	String specificOrders[];	
	List<String> specificOrdersTemp = new ArrayList<String>();
	for (int i = 0; i < orders.length; i++){
		String temp[] = orders[i].split(",");
		if (temp[0].equals(person))specificOrdersTemp.add(orders[i]);//temp[0] is the name
	}
	specificOrders = new String[specificOrdersTemp.size()];
	specificOrdersTemp.toArray( specificOrders ); //converts the arrayList back to an ordinary array.
	
	System.out.format("\n"+"%10s%10s\t    %10s     %7s\n", "Name", "Item", "\t\tConfirmation Id","Total"); //printing formatting
	for (int i = 0; i < specificOrders.length; i++){  //loops through the array
		String temp[] = specificOrders[i].split(","); //creates a temp which splits the string by a comma so different parts can be accessed.
		String Name=temp[0];String Item = temp[1]; String ID= temp[2]; String Total = temp[3]; //associates each part with a variable.
		System.out.format("%10s\t%10s%10s\t\t%10s\n", Name,Item,ID,Total); //print formatting to display nicely to user.
	}
	System.out.println("\n"+"Press -1 to go back: ");
	Scanner userScanner = new Scanner(System.in);
	String selection = checkDecision(userScanner);
	if (Integer.parseInt(selection) == -1) changeCurrentPage(5); //User decides to return to previous menu
	else System.out.println("Not a Valid Option!");
}




public void admin(){ //BONUS 2
	System.out.println("Enter Password: " + "\n"); // asks user for admins password , which is the second line of the Users.txt file 
	System.out.println("Please Note, if password is incorrect, you will be sent back to the sign in/up page"); // if the User types the wrong password , the user is sent to the signin page 
	Scanner userScanner = new Scanner(System.in); // open scanner 
	String userinput = userScanner.nextLine(); // takes whatever the user inputs 
	String lineIWant; // declarations of line i want to get 
	try (FileInputStream fs= new FileInputStream("Users.txt")){ // opens the users.txt files 
	BufferedReader br = new BufferedReader(new InputStreamReader(fs)); // variable br created which reasds file
	for(int i = 1; i <= 2; ++i){ // one  up for loop, which loops through the first tow lines 
	  lineIWant = br.readLine(); // reads the lines
	  adminpassword = lineIWant; // sets the second line as the Admins password and stores it in the global variable adminpassword 
	}
	}catch (IOException e) {
		    //exception handling 
		}
	
	if (userinput.equals(adminpassword)){ // checks if password inputed by the user matches adminpassword 
		adminControl changetoadmin = new adminControl(); // if this is true, it switches control to the adminControl class
		changetoadmin.adminOptions(); // sends to class adminControl 
	}
	else{
		changeCurrentPage(1); // if password is wrong , sends the User to P1(); 
	}
	userScanner.close();
	
}


}
