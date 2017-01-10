/*Name: Corie Bain, Piranaven Selvathayabaran, Darsikan Anandarajah
MacID: bainc2, selvatp, anandad
Student Number: 001436514, 001419766, 001226159
Description: E-commerce Application | Done with Bonus #1 and #2 included.*/
public class eBook extends Readable {
	@Override 
	public int 	getPrice(int SNumber, int quantity){ //getPrice Method is overwritten and a new method is created based on the new requirements
		String tempArray[] = getInfo(SNumber).split(","); //creates a temporary array so elements can be easier accessed.
		int price =  Integer.parseInt(tempArray[3]); //price was stored because it's position was known after splitting.
		price = price* quantity; //previous way to calculate price was overwritten and a new way was done here.
		return price; //price was returned.
	}


}
