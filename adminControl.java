/*Name: Corie Bain, Piranaven Selvathayabaran, Darsikan Anandarajah
MacID: bainc2, selvatp, anandad
Student Number: 001436514, 001419766, 001226159
Description: E-commerce Application | Done with Bonus #1 and #2 included.*/
 // import libraries from java. 
// ASSUMPTION MADE IS THAT ADMIN Is the first Name in the User file 
// AND ADMINS Password is ALWASY THE SECOND LINE IN THE USERs.TXT file 





import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class adminControl {
	public int price; // initialize global variable 
	public int sNo; // initialize global variable 
	public String Name; // intialize global variable 
	public String artistName; // initialize global variable 
	public String authorName; // initialize global variable 
	public int quantity;// initialize global variable 
	public String type;// initialize global variable 
	

	public void adminOptions(){
		// Initial choices displyed to the user 
		System.out.println("Please choose the option you would like (type the number): ");
		System.out.println("1.Add Items");
		System.out.println("2.Sort Items");
		System.out.println("3.Remove Duplicate Users/ Duplicate Users Options");
		System.out.println("4.Change Password ");
		System.out.println("5. Sign out ");
		Scanner userScanner = new Scanner(System.in); // open scanner  
		String userinput = userScanner.nextLine(); // stores what user enters into  userinput variable 
	
		if (userinput.equals("1")){  // conditional,  where if equal 1   
			adminAddItems(); // it sends user and th control to the method AddItems 
		}
		else if(userinput.equals("2")){ // if the userinput equals 2 
			adminSort(); // sends the User and control to adminSort 
		}
		else if(userinput.equals("3")){ // if the userinput equals 3 
			adminDuplicate(); // send the USer and control to adminDuplicate 
		}
		else if(userinput.equals("4")){ // if the userinput equals 4 
			adminPassword(); // sends the user and control to adminPassword 
		}
		else if(userinput.equals("5")){ // if the userinput equals 5
			UserInterface change = new UserInterface(); // make instance of Userinterface
			change.P1(); // send the User and COntrol to P1 
		}
		else{
			System.out.println( "Invalid option. Try again "); // any other input calls the method again 
			adminOptions(); // calls method adminOptions (); 
		}
	userScanner.close();
	}
	
	public void adminAddItems(){
		// Gives Admin the following options, as to which type he would like to sort
		System.out.println("Please select the type of the item you would like to add: ");
		System.out.println("1.Book");
		System.out.println("2.eBook");
		System.out.println("3.CD");
		System.out.println("4.MP3");
		System.out.println("Press any other button to go back to Main");
		Scanner userScanner = new Scanner(System.in); // opens scanner 
		String userinput3 = userScanner.nextLine(); // assigns what the user input as a variable userinput3
		if (userinput3.equals("1")){// if admin types 1 , gets all variables about the item and appends to corresponding txt file 
			type ="Book";  // sets global variable to book 
			System.out.println("Please enter the sNO"); // prompts user 
			String userinput4 = userScanner.nextLine(); // grabs the input from the user through scanner
			sNo=Integer.parseInt(userinput4); // assigns user input to sNO gloabal variable 
			System.out.println("Please enter the Name of the Book"); // prompts user 
			String userinput5 = userScanner.nextLine(); // grabs the input from the user
			Name=(userinput5);// assigns user input to  gloabal variable Name 
			System.out.println("Please enter the Name of the author"); // prompts admin
			String userinput6 = userScanner.nextLine(); // grabs input from the user 
			authorName=(userinput6); // assigns user input to gloabal variable 
			System.out.println("Please enter the price");// prompts admin
			String userinput7 = userScanner.nextLine();// grabs input from the user 
			price=Integer.parseInt(userinput7);// assigns user input to gloabal variable 
			System.out.println("Please enter the Quantity");// prompts admin
			String userinput8 = userScanner.nextLine();// grabs input from the user 
			quantity=Integer.parseInt(userinput8);// assigns user input to gloabal variable 
			String returnString=(sNo+","+Name+","+authorName+ ","+price+","+quantity+","+type); // creates a string with all global variables separted by a comma 
			
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Books.txt", true)))) { // opens up a new PrintWriter, called out, and appendsin txt file 
			    out.println(returnString);// the return string 
			}catch (IOException e) { // cattchesany exceptions  
			    //exception handling left as an exercise for the reader
			}
			System.out.println("Successfully added " + returnString); // prompts the admin that the item is succesfully added +  what was added.
			adminOptions(); // sernd the Admin back to all the options 
		}
		else if(userinput3.equals("2")){
			type ="eBook"; // sets global variable to ebook 
			System.out.println("Please enter the sNO");// prompts user 
			String userinput4 = userScanner.nextLine();// grabs the input from the user through scanner
			sNo=Integer.parseInt(userinput4);// assigns user input to gloabal variable 
			System.out.println("Please enter the Name of the eBook");// prompts user 
			String userinput5 = userScanner.nextLine();// grabs the input from the user through scanner
			Name=(userinput5);// assigns user input to gloabal variable 
			System.out.println("Please enter the Name of the author");// prompts user 
			String userinput6 = userScanner.nextLine();// grabs the input from the user through scanner
			authorName=(userinput6);// assigns user input to gloabal variable 
			System.out.println("Please enter the price");// prompts user 
			String userinput7 = userScanner.nextLine();// grabs the input from the user through scanner
			price=Integer.parseInt(userinput7);// assigns user input to gloabal variable 
			System.out.println("Please enter the Quantity");// prompts user 
			String userinput8 = userScanner.nextLine();// grabs the input from the user through scanner
			quantity=Integer.parseInt(userinput8);// assigns user input to gloabal variable 
			String returnString=(sNo+","+Name+","+authorName+ ","+price+","+quantity+","+type); // creates a string combining all global variables associated with the type 
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("EBooks.txt", true)))) { // opens up a new PrintWriter, called out, and appendsin txt file 
			    out.println(returnString);
			}catch (IOException e) { 
			    //exception handlinng 
			}
			System.out.println("Successfully added " + returnString); // prompts the admin that the item is succesfully added +  what was added.
			adminOptions(); // send the admin back to Optios  
		}
		else if(userinput3.equals("3")){ // if third option is chosen
			type ="CD"; // sets global variable to CD
			System.out.println("Please enter the sNO");// prompts user 
			String userinput4 = userScanner.nextLine();// grabs the input from the user through scanner
			sNo=Integer.parseInt(userinput4);// assigns user input to gloabal variable 
			System.out.println("Please enter the Name of the CD");// prompts user 
			String userinput5 = userScanner.nextLine();// grabs the input from the user through scanner
			Name=(userinput5);// assigns user input to gloabal variable 
			System.out.println("Please enter the Name of the artist");// prompts user 
			String userinput6 = userScanner.nextLine();// grabs the input from the user through scanner
			artistName=(userinput6);// assigns user input to gloabal variable 
			System.out.println("Please enter the price");// prompts user 
			String userinput7 = userScanner.nextLine();// grabs the input from the user through scanner
			price=Integer.parseInt(userinput7);// assigns user input to gloabal variable 
			System.out.println("Please enter the Quantity");// prompts user 
			String userinput8 = userScanner.nextLine();// grabs the input from the user through scanner
			quantity=Integer.parseInt(userinput8);// assigns user input to gloabal variable 
			String returnString=(sNo+","+Name+","+artistName+ ","+price+","+quantity+","+type);// creates a string combining all global variables associated with the type 
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("CDs.txt", true)))) { // opens up a new PrintWriter, called out, and appendsin txt file 
			    out.println(returnString);
			}catch (IOException e) {
			    //exception handling 
			}
			System.out.println("Successfully added " + returnString);// prompts the admin that the item is succesfully added +  what was added.
			adminOptions(); // returns the admin to options 
		}
		else if(userinput3.equals("4")){ // if 4 is chosen 
			type ="MP3"; // sets global variable to MP3
			System.out.println("Please enter the sNO");// prompts user 
			String userinput4 = userScanner.nextLine();// grabs the input from the user through scanner
			sNo=Integer.parseInt(userinput4);// assigns user input to gloabal variable 
			System.out.println("Please enter the Name of the CD");// prompts user 
			String userinput5 = userScanner.nextLine();// grabs the input from the user through scanner
			Name=(userinput5);// assigns user input to gloabal variable 
			System.out.println("Please enter the Name of the artist");// prompts user 
			String userinput6 = userScanner.nextLine();// grabs the input from the user through scanner
			artistName=(userinput6);// assigns user input to gloabal variable 
			System.out.println("Please enter the price");// prompts user 
			String userinput7 = userScanner.nextLine();// grabs the input from the user through scanner
			price=Integer.parseInt(userinput7);// assigns user input to gloabal variable 
			System.out.println("Please enter the Quantity");// prompts user 
			String userinput8 = userScanner.nextLine();// grabs the input from the user through scanner
			quantity=Integer.parseInt(userinput8);// assigns user input to gloabal variable 
			String returnString=(sNo+","+Name+","+artistName+ ","+price+","+quantity+","+type);// creates a string combining all global variables associated with the type 
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("MP3.txt", true)))) { // opens up a new PrintWriter, called out, and appendsin txt file 
			    out.println(returnString);
			}catch (IOException e) {// catch exception 
			    //exception handling
			}
			System.out.println("Successfully added " + returnString);// prompts the admin that the item is succesfully added +  what was added.
			adminOptions(); // sends admin back to options 
		}
		else{
			System.out.println("\n"); // 
			adminOptions(); // sends admin back to options 
		}
		userScanner.close(); // closes sScanner 
		
	}
	
	public void adminSort(){
		//Prompts admin with options with sorting , by type 
		System.out.println("Please select the item type you would like to sort: ");
		System.out.println("And type the corresponding option number: ");
		System.out.println("\n");
		System.out.println("Press any other key to reurn to Options");
		Scanner userScanner3 = new Scanner(System.in); // opens scanner 
		System.out.println("\n");
		System.out.println("Choose type:");
		System.out.println("1.Books:");
		System.out.println("2.eBooks");
		System.out.println("3.CDS");
		System.out.println("4.MP3s");
		System.out.println("Press any other key to return to sorting options");
		
		String userinput5 = userScanner3.nextLine(); // takes user input into variable 
			if (userinput5.equals("1")){ // if variable i 1
				type = "Books"; // sets type to book 
				//prompts the admin with all his sorting options 
				System.out.println("1.Sort By Price High to Low");
				System.out.println("2.Sort By Price Low to High ");
				System.out.println("3.Sort By Name A-Z");
				System.out.println("4.Sort By Name Z-A ");
				System.out.println("Press any other key to return to Sort Options");
				String userinput4 = userScanner3.nextLine(); // reads what the userinputs and sets as variable 
				// based on the input,  method to build array is called thourgh creatnig an instance
				// then the resulting array is sent to the correct sorting method 
				// then  the admin is sent back to the sorting  menu. 
				if (userinput4.equals("1")){
					UserInterface getInfo = new UserInterface();	
					String [] books = getInfo.buildArray("Books.txt");
					sortHightoLow(books);
					adminSort();
				}else if(userinput4.equals("2")){
					UserInterface getInfo = new UserInterface();	
					String [] books = getInfo.buildArray("Books.txt");
					sortLowtoHigh(books);
					adminSort();
				}
				else if(userinput4.equals("3")){
					UserInterface getInfo = new UserInterface();	
					String [] books = getInfo.buildArray("Books.txt");
					sortAtoZ(books);
					adminSort();
				}
				else if(userinput4.equals("4")){
					UserInterface getInfo = new UserInterface();	
					String [] books = getInfo.buildArray("Books.txt");
					sortZtoA(books);
					adminSort();
				}
				else{
					
					adminSort();
				}
			}
			else if(userinput5.equals("2")){
				type = "EBooks";
				//prompts the admin with all his sorting options 
				System.out.println("1.Sort By Price High to Low");
				System.out.println("2.Sort By Price Low to High ");
				System.out.println("3.Sort By Name A-Z");
				System.out.println("4.Sort By Name Z-A ");
				System.out.println("Press any other key to return to Sort Options");
				String userinput4 = userScanner3.nextLine(); // reads what the userinputs and sets as variable
				// based on the input,  method to build array is called thourgh creatnig an instance
				// then the resulting array is sent to the correct sorting method 
				// then  the admin is sent back to the sorting  menu. 
				if (userinput4.equals("1")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("EBooks.txt");
					sortHightoLow(ebooks);		
					adminSort();
				}else if(userinput4.equals("2")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("EBooks.txt");
					sortLowtoHigh(ebooks);	
					adminSort();
				}
				else if(userinput4.equals("3")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("EBooks.txt");
					sortAtoZ(ebooks);
					adminSort();
				}
				else if(userinput4.equals("4")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("EBooks.txt");
					sortZtoA(ebooks);
					adminSort();
				}
				else{
					
					adminSort();
				}
			}
			else if(userinput5.equals("3")){
				type = "CDs";
				//prompts the admin with all his sorting options 
				System.out.println("1.Sort By Price High to Low");
				System.out.println("2.Sort By Price Low to High ");
				System.out.println("3.Sort By Name A-Z");
				System.out.println("4.Sort By Name Z-A ");
				System.out.println("Press any other key to return to Sort Options");
				 // reads what the userinputs and sets as variable
				String userinput4 = userScanner3.nextLine();
				// based on the input,  method to build array is called thourgh creatnig an instance
				// then the resulting array is sent to the correct sorting method 
				// then  the admin is sent back to the sorting  menu. 
				if (userinput4.equals("1")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("CDs.txt");
					sortHightoLow(ebooks);	
					adminSort();
				}else if(userinput4.equals("2")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("CDs.txt");
					sortLowtoHigh(ebooks);	
					adminSort();
				}
				else if(userinput4.equals("3")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("CDs.txt");
					sortAtoZ(ebooks);
					adminSort();
				}
				else if(userinput4.equals("4")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("CDs.txt");
					sortZtoA(ebooks);
					adminSort();
				}
				else{
					
					adminSort();
				}
			}
			else if(userinput5.equals("4")){
				type = "MP3";
				//prompts the admin with all his sorting options 
				System.out.println("1.Sort By Price High to Low");
				System.out.println("2.Sort By Price Low to High ");
				System.out.println("3.Sort By Name A-Z");
				System.out.println("4.Sort By Name Z-A ");
				System.out.println("Press any other key to return to Sort Options");
				String userinput4 = userScanner3.nextLine(); // reads what the userinputs and sets as variable
				// based on the input,  method to build array is called thourgh creatnig an instance
				// then the resulting array is sent to the correct sorting method 
				// then  the admin is sent back to the sorting  menu. 
				if (userinput4.equals("1")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("MP3.txt");
					sortHightoLow(ebooks);	
					adminSort();
				}else if(userinput4.equals("2")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("MP3.txt");
					sortLowtoHigh(ebooks);
					adminSort();
				}
				else if(userinput4.equals("3")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("MP3.txt");
					sortAtoZ(ebooks);
					adminSort();
				}
				else if(userinput4.equals("4")){
					UserInterface getInfo = new UserInterface();	
					String [] ebooks = getInfo.buildArray("MP3.txt");
					sortZtoA(ebooks);
					adminSort();
				}
				else{
					
					adminSort();
				}
			}
			else{
				
				adminOptions();
			}
		userScanner3.close();	
		}

	
	public void adminDuplicate(){
		//prompts user with optons
		System.out.println("1.Delete all duplicates usernames");
		System.out.println(" 2. Stop Users from Making Duplicate Usernames ");
		Scanner userScanner3 = new Scanner(System.in); // opens new scanner
		String input = userScanner3.nextLine(); // assigns what the user inputs to variables 
		if ( input.equals("1")){ // if equal to 1
		UserInterface getInfo = new UserInterface();	
		// calls instance from  Userinterface, calls buildarray method 
		String [] Users = getInfo.buildArray("Users.txt");
		// gets array from sending txt file locatinon
		
		// double for loop that compares each exracted formthe users.txt with the preceeding lines.
		// if there is a duplicate, it sets the duplicate value to 0  
		 for (int i = 0; i < Users.length; i++) {
		     for (int j = i + 1 ; j < Users.length; j++) {
		          if (Users[i].equals(Users[j])) {
		        	  Users[j] = "0"; 
		                   
		          }
		     }
		 }
		 try {
		    	
	            // Assume default encoding.
	            FileWriter fileWriter =
	                new FileWriter("Users.txt"); // makes new fileWrites 

	            // Always wrap FileWriter in BufferedWriter.
	            BufferedWriter bufferedWriter =
	                new BufferedWriter(fileWriter); // new bufferedWriter

	           //for loops that writes to txt file the users , line by line , leaving all values equaling 0 out. 
	            for (int i=0; i<Users.length; i++){
	            	if (!Users[i].equals("0")){
	            		 bufferedWriter.write(Users[i]);
		            	  bufferedWriter.write("\n");
	            	}
	            	 
	            }	            

	            // close files.
	            bufferedWriter.close();
	        }
	        catch(IOException ex) { // error exceptino handling 
	            System.out.println(
	                "Error writing to file '"
	                + "'");
	            // Or we could just do this:
	            // ex.printStackTrace();
	        }
		 System.out.println( "Duplicate removed.");// prompts user that buplicates are removed
			adminOptions();
		 
		 
		}
		else if (input.equals("2")){
			
		}
		else{
			adminOptions();
		}
	userScanner3.close();
	}
	public void adminPassword(){ // method that changes the admins password 
		Scanner userScanner2 = new Scanner(System.in); //opens scanner 
		System.out.println("Please enter your new password:"); // prompts user 
		String passwordinput = userScanner2.nextLine();	// takes userinput to variable 
		UserInterface userpass = new UserInterface();
		userpass.adminpassword = passwordinput;// changes global variable to the new password 
		//int counter = 0;  // sets counter 
		String[] stringArr = userpass.buildArray("Users.txt"); //  calls build array method , and adds it users to an array 
		stringArr[1] = passwordinput; // sets the second line to the new password 
        try {
    	
            //open filewtire 
            FileWriter fileWriter =
                new FileWriter("Users.txt");

            //  BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

           // for loop that goes through the array and writres to txt file 
            for (int i=0; i<stringArr.length; i++){
            	  bufferedWriter.write(stringArr[i]);
            	  bufferedWriter.write("\n");
            }
          
            

            //close files.
            bufferedWriter.close();
        }
        catch(IOException ex) { // catch exceptions 
            System.out.println(
                "Error writing to file '"
                + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    
		System.out.println("Your new Password has been set. "); // prompts user
		adminOptions();// send admin back to options 

	userScanner2.close();
	}	
	
	public void sortAtoZ(String array[]){ // sorting array that sorts ana array from A to Z
		int counter = 0 ; // initialiaze counter to zero
	/// this for loop, calculates the size of the given array , by incrementing a coutner 
		for (int i = 0; i < array.length; i ++){ // this is a for loop, that counts how many elements are in he array
		    if (array[i] != null)
		        counter ++;
		}
		// create a 2darray with length of counter and width of 6 ( all details ) 
		// these for loops, take the array line by line,  splits its conetents and places them neatly in the 2d array 
		String [][] array2d = new String[counter][6];
		for(int i=0 ; i<array.length; i++){
			String tempArray[] = array[i].split(","); // using for loop, takes the first element(which is a string ), splits it into a temp array
			for (int k= 0; k < array2d.length; k++) {
			    for (int j = 0; j < array2d[0].length; j++) {
			    	array2d[i][j] = tempArray[j];
			    	}
			    }
		}
		// this java convention, sorts the 2d array , comparing each value in the coloumn of the array2d and sorts 
        Arrays.sort(array2d, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = entry1[1];
                final String time2 = entry2[1];
                return time1.compareTo(time2);
            }
        });

        try {
        	
            // new file writer 
            FileWriter fileWriter =
                new FileWriter(""+type+".txt");

            // Wraps FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            //for loop that goes thorugh array and prints out each line with a comma inbeween and also writes to text file it content.   
            for (final String[] s : array2d) {
                System.out.println(s[0] + "," + s[1]+ "," + s[2]+ "," + s[3]+ "," + s[4]+ "," + s[5]);
                bufferedWriter.write(s[0] + "," + s[1]+ "," + s[2]+ "," + s[3]+ "," + s[4]+ "," + s[5]);
                bufferedWriter.write("\n");
            }
          
            

            //  close files.
            bufferedWriter.close();
        }
        catch(IOException ex) { // catches exceptions 
            System.out.println(
                "Error writing to file '"
                + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
		
	}
	public void sortZtoA(String array[]){ // sorting array that sorts ana array from z to A
		int counter = 0 ;// initializaes counter to 0; 
		/// this for loop, calculates the size of the given array , by incrementing a coutner 
		for (int i = 0; i < array.length; i ++){ // this is a for loop, that counts how many elements are in he array
		    if (array[i] != null)
		        counter ++;
		}
		// create a 2darray with length of counter and width of 6 ( all details ) 
		// these for loops, take the array line by line,  splits its conetents and places them neatly in the 2d array 
		String [][] array2d = new String[counter][6];
		for(int i=0 ; i<array.length; i++){
			String tempArray[] = array[i].split(","); // using for loop, takes the first element(which is a string ), splits it into a temp array
			for (int k= 0; k < array2d.length; k++) {
			    for (int j = 0; j < array2d[0].length; j++) {
			    	array2d[i][j] = tempArray[j];
			    	}
			    }
		}
		// this java convention, sorts the 2d array , comparing each value in the coloumn of the array2d and sorts 
        Arrays.sort(array2d, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = entry1[1];
                final String time2 = entry2[1];
                return time2.compareTo(time1);
            }
        });

        try {
        	//new fileWriter opening correct file 
            FileWriter fileWriter =
                new FileWriter(""+type+".txt");

            //  wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

          //for loop that goes thorugh array and prints out each line with a comma inbeween and also writes to text file it content.   
            for (final String[] s : array2d) {
                System.out.println(s[0] + "," + s[1]+ "," + s[2]+ "," + s[3]+ "," + s[4]+ "," + s[5]);
                bufferedWriter.write(s[0] + "," + s[1]+ "," + s[2]+ "," + s[3]+ "," + s[4]+ "," + s[5]);
                bufferedWriter.write("\n");
            }
          
            

            // close files.
            bufferedWriter.close();
        }
        catch(IOException ex) { // exception handling 
            System.out.println(
                "Error writing to file '"
                + "'");
           
            // ex.printStackTrace();
        }
		
	
		
	}
	public void sortHightoLow(String array[]){ // sorting array that sorts an a array from high to low
		int counter = 0 ;  // initializes counter to zero.  
		/// this for loop, calculates the size of the given array , by incrementing a coutner 
		for (int i = 0; i < array.length; i ++){ // this is a for loop, that counts how many elements are in he array
		    if (array[i] != null)
		        counter ++;
		}
		// create a 2darray with length of counter and width of 6 ( all details ) 
		// these for loops, take the array line by line,  splits its conetents and places them neatly in the 2d array 

		String [][] array2d = new String[counter][6];
		for(int i=0 ; i<array.length; i++){
			String tempArray[] = array[i].split(","); // using for loop, takes the first element(which is a string ), splits it into a temp array
			for (int k= 0; k < array2d.length; k++) {
			    for (int j = 0; j < array2d[0].length; j++) {
			    	array2d[i][j] = tempArray[j];
			    	}
			    }
		}
		// this java convention, sorts the 2d array , comparing each value in the coloumn of the array2d and sorts 
        Arrays.sort(array2d, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = (entry1[3]);
                final String time2 = (entry2[3]);
                return time2.compareTo(time1);
            }
        });

        try {
        	
            //opens file writer , with correct txt file. 
            FileWriter fileWriter =
                new FileWriter(""+type+".txt");

            // wraps FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);


          //for loop that goes thorugh array and prints out each line with a comma inbeween and also writes to text file it content.   
            for (final String[] s : array2d) {
                System.out.println(s[0] + "," + s[1]+ "," + s[2]+ "," + s[3]+ "," + s[4]+ "," + s[5]);
                bufferedWriter.write(s[0] + "," + s[1]+ "," + s[2]+ "," + s[3]+ "," + s[4]+ "," + s[5]);
                bufferedWriter.write("\n");
            }
          
            

            // close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {// exceptions handling 
            System.out.println(
                "Error writing to file '"
                + "'");
            // Or we could just do this:
           
        }
		
	}
		
	
	
	public void sortLowtoHigh(String array[]){ // sorting array that sorts ana array from low to high 
		
		int counter = 0 ; //initilizes counter to zero
		/// this for loop, calculates the size of the given array , by incrementing a coutner 
		for (int i = 0; i < array.length; i ++){ // this is a for loop, that counts how many elements are in he array
		    if (array[i] != null)
		        counter ++;
		}
		// create a 2darray with length of counter and width of 6 ( all details ) 
		// these for loops, take the array line by line,  splits its conetents and places them neatly in the 2d array 

		String [][] array2d = new String[counter][6];
		for(int i=0 ; i<array.length; i++){
			String tempArray[] = array[i].split(","); // using for loop, takes the first element(which is a string ), splits it into a temp array
			for (int k= 0; k < array2d.length; k++) {
			    for (int j = 0; j < array2d[0].length; j++) {
			    	array2d[i][j] = tempArray[j];
			    	}
			    }
		}
		// this java convention, sorts the 2d array , comparing each value in the coloumn of the array2d and sorts 
        Arrays.sort(array2d, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = (entry1[3]);
                final String time2 = (entry2[3]);
                return time1.compareTo(time2);
            }
        });

        try {
        	
            // open filewriter with correct file to read.
            FileWriter fileWriter =
                new FileWriter(""+type+".txt");

            // wraps FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

          //for loop that goes thorugh array and prints out each line with a comma inbeween and also writes to text file it content.   
            for (final String[] s : array2d) {
                System.out.println(s[0] + "," + s[1]+ "," + s[2]+ "," + s[3]+ "," + s[4]+ "," + s[5]);
                bufferedWriter.write(s[0] + "," + s[1]+ "," + s[2]+ "," + s[3]+ "," + s[4]+ "," + s[5]);
                bufferedWriter.write("\n");
            }
          
            

            // close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {//exceptions handling 
            System.out.println(
                "Error writing to file '"
                + "'");
            // Or we could just do this:

        }
	}
	
}
	
	
	

