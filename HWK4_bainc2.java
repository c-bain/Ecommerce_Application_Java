

/*Name: Corie Bain, Piranaven Selvathayabaran, Darsikan Anandarajah
MacID: bainc2, selvatp, anandad
Student Number: 001436514, 001419766, 001226159
Description: E-commerce Application | Done with Bonus #1 and #2 included.
*****************************************
MOST IMPORTANT: EVERY TIME THE PROGRAM IS RAN AGAIN FROM SCRATCH(PRESSING THE RUN BUTTON) ADMIN IS POPULATED ON THE 
				LAST TWO LINES OF USERS.TXT FOR THE IMPLENTATION OF THE BONUS#2. THIS DOES NOT IMPACT FUNCTIONALITY 
				BUT IT IS RECOMMENDED THAT THE RUN BUTTON IS PRESSED ONCE TO GET AN ORDERLY DISPLAY OF THE USERS.TXT 
				FILE. IF THE USER WISHES TO START AGAIN FROM THE INITIAL PAGE, USE THE IN PROGRAM NAVIGATION TO SIGN 
				OUT THEN SIGN BACK IN.

 
/*IMPORTANT: 
 -In order to run, at the beginning, the program requires the text files: Books.txt , CDs.txt, Ebooks.txt, MP3.txt
 These can be made by the user. These are the only things required, everything else is created.
 -A Users.txt is automatically created with ADMIN on both line 1 and 2. This is used to implement admin features.
 -Initially there are no users, you must signup and then sign in with the user you created.
 -The user's cart is deleted at the very end of the program when they complete checkout.
 -To initiate bonus#2 Login with the username ADMIN and password ADMIN
 -When books,ebooks,cd,mp3 text files are put in the directory of the program, when the program begins those files will
 be cleaned to remove unnecessary white spaces and blank lines.


*/				


public class HWK4_bainc2 {
	public static void main(String args[]) {
		UserInterface start = new UserInterface(); //an instance of the UserInterface Class was created.
		start.cleanFiles(); //Formats, Books,Ebooks,CD,MP3 files to remove unnecessary white spaces and blank lines.
		start.P1(); //Main calls the first page and the start to the program, P1
	}
}

