/*Name: Corie Bain, Piranaven Selvathayabaran, Darsikan Anandarajah
MacID: bainc2, selvatp, anandad
Student Number: 001436514, 001419766, 001226159
Description: E-commerce Application | Done with Bonus #1 and #2 included.*/
import java.util.ArrayList;
import java.util.List;


public class Readable extends Item {
	protected String authorName; //Stores the authorName in a protected variable
	public String[] array; //array used to getInfo
	public String[] jointArray; //all products
	
	public void getReadables() {   //This method creates an array from all the readables in the Books.txt file.
		UserInterface ui = new UserInterface();
		String bookList[]=ui.buildArray("Books.txt"); //Builds an array of all books
		String eBooksList[]=ui.buildArray("Ebooks.txt");  //Builds an array of eBoooks
		List<String> readablesTemp = new ArrayList<String>(); //creates a temporary empty arraylist to put the contents of the books and eBooks. An arrayList was used to make appending easier.
		for (int i =0; i<bookList.length;i++){ 
			readablesTemp.add(bookList[i]);  //Adds all books to the temporary array.
		}
		for (int i =0; i<eBooksList.length;i++){ //Adds all ebooks to the temporary array.
			readablesTemp.add(eBooksList[i]);
		}
		this.jointArray= new String[ readablesTemp.size() ]; 
		readablesTemp.toArray(jointArray); //converts the arrayList back to an ordinary array.
	}
	
	public String getInfo(int sNumber){ //Method get info returns a string based on the line chosen by the user.
		getReadables(); //method used to build and display readables
		for(int i=0; i< jointArray.length; i++ ){ //loops through jointArray
			array = jointArray[i].split(",");  //splits by line for easier access to elements
			if(Integer.parseInt(array[0]) == sNumber){ //if the selection the user made matches with an SNo, this is the line needed.
				sNO = Integer.parseInt(array[0]);  //since the line was found, the respective sNo was stored.
				name = array[1]; //since the line was found, the respective name was stored.
				authorName=array[2]; //since the line was found, the respective authorName was stored.
				price=Integer.parseInt(array[3]); //since the line was found, the respective price was stored.
				quantity = Integer.parseInt(array[4]); //since the line was found, the respective quantity was stored.
				type = array[5]; //since the line was found, the respective type was stored.
			}
		}
		String returnString = sNO+","+name+","+authorName+","+price+","+quantity+","+type;
			return returnString; //the string the user requested is returned.
	}
	@Override
	public int getPrice(int sNumber, int quantity){ //index, same as line number .
		// TODO Auto-generated method stub
		String tempArray[] = getInfo(sNumber).split(","); //creates a temporary array using the getInfo method. The different fields are split by commas.
		int price =  Integer.parseInt(tempArray[3]); //tempArray[3] holds the price. That is stored in a new variable price.The price variable is updated in the item class.
		price = price * quantity; //calculates the new prices based on the quantity of products bought. 
		return price ;  //the price is returned.
	}
}
