
public class Contact {

	private String Title;
	private String FirstN;
	private String LastN;
	private String Id;
	private String Email;
	private String HomeWork;
	private String Quizzes;
	private String Lab;
	private String MidTerm;
	private String ClassTest;
	private String Final;
	private String TotalGrade;
	private String LetterGrade;


	public Contact(String a,String b, String c,String d,String f, String g, String e, String k, String j, String h, String m, String x, String q ){


		this.Title = a;
		this.FirstN = b;
		this.LastN = c;
		this.Id = d;
		this.Email = f;
		this.HomeWork = g;
		this.Quizzes = e;
		this.Lab = k;
		this.MidTerm = j;
		this.ClassTest = h;
		this.Final = m;
		this.TotalGrade = x;
		this.LetterGrade = q;

	}

	public Contact(String[] a){
		if(a.length==13){
		this.Title = a[0];
		this.FirstN = a[1];
		this.LastN = a[2];
		this.Id = a[3];
		this.Email = a[4];
		this.HomeWork = a[5];
		this.Quizzes = a[6];
		this.Lab = a[7];
		this.MidTerm = a[8];
		this.ClassTest = a[9];
		this.Final = a[10];
		this.TotalGrade = a[11];
		this.LetterGrade = a[12];
		} else {
			this.Title = a[0];
			this.FirstN = a[1];
			this.LastN = a[2];
			this.Id = a[3];
			this.Email = a[4];
		}
	}

	public String getTitle(){
		return Title;
	}

	public String getFirstN(){
		return FirstN;
	}


	public String getLastN(){
		return LastN;
	}


	public String getId(){
		return Id;
	}


	public String getEmail(){
		return Email;
	}


	public String getHomeWork(){
		return HomeWork;
	}


	public String getQuizzes(){
		return Quizzes;
	}


	public String getLab(){
		return Lab;
	}


	public String getMidTerm(){
		return MidTerm;
	}


	public String getClassTest(){
		return ClassTest;
	}


	public String getFinal(){
		return Final;
	}


	public String getTotalGrade(){
		return TotalGrade;
	}

	public String getLetterGrade(){
		return LetterGrade;
	}

	public void setTitle(String t){
	     this.Title=t;
	}

	public void setFirstN(String t){
		this.FirstN = t;
	}


	public void setLastN(String t){
		this.LastN=t;
	}


	public void setId(String t){
		this.Id=t;
	}


	public void setEmail(String t){
		this.Email=t;
	}


	public void setHomeWork(String t){
		this.HomeWork=t;
	}


	public void setQuizzes(String t){
		this.Quizzes=t;
	}


	public void setLab(String t){
		this.Lab=t;
	}


	public void setMidTerm(String t){
		this.MidTerm=t;
	}


	public void setClassTest(String t){
		this.ClassTest=t;
	}


	public void setFinal(String t){
		this.Final=t;
	}


	public void setTotalGrade(String t){
		this.TotalGrade=t;
	}

	public void setLetterGrade(String t){
		this.LetterGrade=t;
	}

	public String toString(){
		return Title +","+ FirstN +"," + LastN+","+Id +","+ Email +"," + HomeWork+"," + Quizzes +","+ Lab +"," + MidTerm+"," +ClassTest +","+ Final +"," + TotalGrade+","+ LetterGrade;
	}

	
}
