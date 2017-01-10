/*Name: Corie Bain, Piranaven Selvathayabaran, Darsikan Anandarajah
MacID: bainc2, selvatp, anandad
Student Number: 001436514, 001419766, 001226159
Description: E-commerce Application | Done with Bonus #1 and #2 included.*/
import java.util.ArrayList;
import java.util.List;

public class Audio extends Item {
	protected String artistName;
	public String[] jointArray; //all products

	public void getAudioProducts() { //This method creates an array from all the audiobooks in the Books.txt file.
		UserInterface ui = new UserInterface();
		String cdList[]=ui.buildArray("CDs.txt"); //Builds an array of all books
		String mp3List[]=ui.buildArray("MP3.txt");  //Builds an array of eBoooks
		List<String> readablesTemp2 = new ArrayList<String>(); //creates a temporary empty arraylist to put the contents of the books and eBooks. An arrayList was used to make appending easier.
		for (int i =0; i<cdList.length;i++){ 
			readablesTemp2.add(cdList[i]);  //Adds all books to the temporary array.
		}
		for (int i =0; i<mp3List.length;i++){ //Adds all ebooks to the temporary array.
			readablesTemp2.add(mp3List[i]);
		}
		this.jointArray = new String[ readablesTemp2.size() ]; 
		readablesTemp2.toArray( jointArray); //converts the arrayList back to an ordinary array.
	}
	
	public String getInfo(int sNumber){ //getInfo meth takes the sNo
		getAudioProducts();  //displays the array of audioProducts to the user
		String [] array; //initializes an empty String array
		for(int i=0; i< jointArray.length; i++ ){ //loops through the jointArray
			array = jointArray[i].split(",");  //creates a temporary string split by commas for accessing values.
			if(Integer.parseInt(array[0]) == sNumber){ //checks if current value is equal to the sNo number
				sNO = Integer.parseInt(array[0]);  //if it is, this is the needed line and the sNO is stored
				name = array[1]; //as well as name of the item
				artistName=array[2];  //as well as author/artist of the item
				price=Integer.parseInt(array[3]);  //as well as the price of the item
				quantity = Integer.parseInt(array[4]);  //as well as the quantity of the item
				type = array[5];  //as well as the type of the item
			}
		}
		String returnString = sNO+","+name+","+artistName+","+price+","+quantity+","+type;//a string of the line is built
		return returnString; //a string of the line is returned
	}
	
	@Override
	public int getPrice(int sNumber, int quanity){ //This method uses the getInfo method to retrieve the price.
		// TODO Auto-generated method stub
		String tempArray[] = getInfo(sNumber).split(","); //creates a temporary array using the getInfo method. The different fields are split by commas.
		int price =  Integer.parseInt(tempArray[3]); //tempArray[3] holds the price. That is stored in a new variable price.The price variable is updated in the item class.
		price = price *quantity; 
		return price ;
	}
}
