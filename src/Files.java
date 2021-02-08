import java.io.*;
import java.util.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class Files  {

	private String file_name;
	private File file;
	Contact[] co;
	static String[] s;



	public Files(String n){
		this.file_name = n;
	}

	public Files(){

	}

	public boolean checkF(){
		 file = new File(file_name);
		if(file.exists())
			return true;
		else
			return false;
	}



	public void read(String file_name) throws IOException{

	String row ;
	List<String> ar = new ArrayList<String>();
	   int n = 0;
	BufferedReader csvReader = new BufferedReader(new FileReader(file_name));
	while ((row = csvReader.readLine()) != null) {
		ar.add(n,row);
		n++;
	}

		co = new Contact[ar.size()];
	for(int i=0 ; i<ar.size() ; i++){

		co[i] = new Contact(ar.get(i).split(","));

	//	System.out.println(co[i].toString());
	}
	csvReader.close();
	}
	
	
	public void write() throws IOException{
		PrintWriter outputStream;
		Scanner in = new Scanner(System.in);
	PrintWriter w = new PrintWriter(new FileOutputStream(file_name,true));
	System.out.println("enter the title");
	String Title = in.nextLine();
	//w.print(Title);
	System.out.println("enter the full name");
	String fullName = in.nextLine();
	//w.print(fullName);
	System.out.println("enter the Email");
	String email = in.nextLine();
	//w.print(email);
	System.out.println("enter the Mail adress");
	String address = in.nextLine();
	//w.print(address);

	String contact = Title+","+fullName+","+email+","+address;

	w.println(contact);
	w.close();
	in.close();

	}



	public boolean makePDF(String s)
    {
		Scanner in = null;
		int n=0;

    	  try {
    	    //You should change the file name
            OutputStream file = new FileOutputStream(new File("Test.pdf"));

	    //keep the following 3 lines unchanged
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();

            //generate 10 pages
 /*changed*/          for(int i=1; i<co.length;i++)
            {

	 try{
		 in = new Scanner(s);
}catch(Exception e){

}


//while(in.hasNext()){
//	if(in.next().equals("[[TITLE]]"))
//		n++;
//	document.add(new Paragraph(co[i].getTitle()));
//	//go to new page
//
//
//}
//document.newPage();

    String s1 = s.replace("[[TITLE]]", co[i].getTitle());
    String s2 = s1.replace("[[FIRST_NAME]]", co[i].getFirstN());
	String s3 = s2.replace("[[LAST_NAME]]", co[i].getLastN());
    String s4 = s3.replace("[[ID_NUMBER]]", co[i].getId());
    String s5 = s4.replace("[[EMAIL_ADDRESS]]", co[i].getEmail());
    String s6 = s5.replace("[[HW]]", co[i].getHomeWork());
    String s7 = s6.replace("[[QUIZZES]]", co[i].getQuizzes());
    String s8 = s7.replace("[[LAB]]", co[i].getLab());
    String s9 = s8.replace("[[MIDTERM]]", co[i].getMidTerm());
    String s10 = s9.replace("[[CLASS_TESTS]]", co[i].getClassTest());
    String s11 = s10.replace("[[FINAL]]", co[i].getFinal());
    String s12 = s11.replace("[[TOTAL]]", co[i].getTotalGrade());
    String s13 = s12.replace("[[LETTER_GRADE]]", co[i].getLetterGrade());

                document.add(new Paragraph(s13));
				//go to new page
				document.newPage();
            }
            document.close();
            file.close();
            return true;
        }catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
	
	
	
	
	public static void main(String[] args){
		for(int i=0;i<s.length;i++){
		System.out.println(s[i]);
		}
	}


}