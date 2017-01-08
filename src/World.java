import java.util.ArrayList;
import java.io.IOException;

import java.util.Random;

public class World {
	private Avatar a;
	private ArrayList<Room> WorldRooms;
	private ArrayList<Teacher> allTeachers;
	private ArrayList<Student> allStudents;
	private ArrayList<Book> allBooks;
	private ArrayList<Course> allCourses;
	private ArrayList<Key> allKeys;


	


	/**
	 * Constructor for world class
		 */
	public World() throws IOException{
	this.WorldRooms = new ArrayList<Room>();
	this.allTeachers = new ArrayList<Teacher>();
	this.allBooks = new ArrayList<Book>();
	this.allCourses = new ArrayList<Course>();
	this.allKeys = new ArrayList<Key>();
	}


	
//==============================================
//Getters
//==============================================

/**
 * @return The arraylist of all the rooms in the world.
	 */
	public ArrayList<Room> returnWorldRooms() {
		return this.WorldRooms;
	}

	/**
	 * @return The arraylist of all the teachers in the world.
		 */
	public ArrayList<Teacher> returnAllTeachers() {
		return this.allTeachers;
	}

	/**
	 * @return The arraylist of all the courses in the world.
		 */
	public ArrayList<Course> returnAllCourses() {
		return this.allCourses;
	}

	/**
	 * @return The arraylist of all the books in the world.
		 */
	public ArrayList<Book> returnAllBooks() {
	return this.allBooks;
	}

//OBS
	//Do we need this?
	public ArrayList<Key> returnAllKeys() {
	return this.allKeys;
	}


//==============================================

//ADDING STUFF HERE
/**
 * Creates the games World.
 * @param teacherFile a file containing all the teachers on format: ....
 * @param bookFile a file containing all the books on format: ....
 * @param roomFile a file containing all the rooms on format: ....
	 */
public void createWorld(String teacherFile, String bookFile, String roomFile)throws IOException{
	this.createBooks(bookFile);
	this.createCoursesList(teacherFile);
	this.createTeacherList(teacherFile);
	this.createRooms(roomFile);
}

//=======================================================

/**
 * Finds a course from a string representing it's name.
 * @param s the name of the course one want to find.
 * @return the course searched for.
	 */
public Course returnCourseFromQuestion(String s){
	Course c = null;
	for(int i = 0; i < allCourses.size(); ++i){
		if(allCourses.get(i).returnQuestion().equals(s)){
			c = allCourses.get(i);
			return c;
		}
	}
	return c;
}

//I don't get this function? Isn't Course c already the course we look for?
/**
 * Finds a course.
 * @param c the name of the course one want to find.
 * @return the course searched for.
	 */
public Course findcourse(Course c){
		for(int i = 0; i < allCourses.size(); ++i){
			if(allCourses.get(i).returnCourseName().equals(c.returnCourseName())){
				return allCourses.get(i);
			}
		}
	return null;
}


/**
 * Returns a random course.
 * @return the generated course.
	 */
public Course returnRandomCourse(){
	Random r = new Random();
	int randomIndex= r.nextInt(allCourses.size());
	return allCourses.get(randomIndex);
}



//======================================================

/**
 * Reads from a file and adds courses to the ListArray of courses.
 * @param filePath the path to the file containing info about the courses.
	 */
public void createCoursesList(String filePath) throws IOException{
String[] array = this.readToArray(filePath);
for (String s : array) {
String[] course =s.split(";");
//Change to: Get the right book!
int year = Integer.parseInt(course[9]);
int edition = Integer.parseInt(course[10]);
Book b = new Book(course[2],course[8], year, edition);
int HP = Integer.parseInt(course[3]);
String question = course[4];
String alternativeOne = course[5];
String alternativeTwo = course[6];
String answer = course[7];
Course c = new Course(course[1], b, HP,question,alternativeOne, alternativeTwo,answer);
this.allCourses.add(c);

}
}

/**
 * Generates a random book.
 * @return the generated book.
	 */
public Book randomBook(){
	Random randomGenerator = new Random();
    int index = randomGenerator.nextInt(this.allBooks.size());
    Book b = this.allBooks.get(index);
    return b;
}

//todo: Make sure that finished cours is not the same as unfinis course
/**
 * Creates a random student. The student gets a random name, and random courses.
 * @return the generated student.
	 */
public Student createRandomStudent(){
	Random a = new Random();
	String names[] = {"Maria","Lucas","Peter","Sara","Anna","Linus","Joana","Siri","Lina","Jakob","Jefar","Nina","Hanna","Joel","Obama","Mulugeta","Rahel","Christina" };
	int randomIndex= a.nextInt(18);
	int randomCourseIndex1 = a.nextInt(this.allCourses.size());
	int randomCourseIndex2 = a.nextInt(this.allCourses.size());

	String studentName = names[randomIndex];
	Course unfinished =this.allCourses.get(randomCourseIndex1);
	Course finished =this.allCourses.get(randomCourseIndex2);
	Student s = new Student(studentName, finished, unfinished);
	return s;
}

//=============================
/**
 * Reads from a file and adds courses to the ListArray of teachers.
 * @param filePath the path to the file containing info about the teachers.
	 */
	public void createTeacherList(String filePath) throws IOException{
		String[] array = this.readToArray(filePath);
		for (String s : array) {
				String[] oneTeacher =s.split(";");
				Teacher t = new Teacher(oneTeacher[0]);
				int HP = Integer.parseInt(oneTeacher[3]);
				Book b = this.allBooks.get(0);
				String question = oneTeacher[4];
				String alternativeOne = oneTeacher[5];
				String alternativetwo = oneTeacher[6];
				String answer = oneTeacher[7];
				Course c = new Course(oneTeacher[1], b, HP, question,alternativeOne, alternativetwo,answer);
				t.connectCourse(c);
				this.allTeachers.add(t);
			}
		}



//==============================================

/**
 * Reads from a file and adds courses to the ListArray of rooms.
 * The room gets updated NextRooms and DoorsOpenStatus.
 * The room gets a random teacher and student.
 * The room gets a random key and book.
 * The room might get a sfinx added to the room. (Depends on room file)
 * @param filePath the path to the file containing info about the rooms.
	 */
public void createRooms(String filePath) throws IOException{
	String[] array = this.readToArray(filePath);
	for (String s : array) {
		String[] RoomsAttributes =s.split(";");
		Room r = new Room(RoomsAttributes[0]);
		r.updateNextRooms(RoomsAttributes);
		r.updateDoorStatus(RoomsAttributes);
		r.addRandomTeacher(this.allTeachers);
		Student student= createRandomStudent();
		r.addRandomStudent(student);
		Key key = randomcreateKey();
		r.addRandomKey(key);
		Book b = randomBook();
		r.addBookToRoom(b);
		if(!RoomsAttributes[9].equals("null")) {
			Sfinxen sfin = new Sfinxen(RoomsAttributes[9]);
			r.addSfinxen(sfin);
		}
		else{
			Sfinxen sfin = new Sfinxen("null");
			r.addSfinxen(sfin);
		}
		this.WorldRooms.add(r);
	}
}



//===============================================
/**
 * Reads from a file and adds courses to the ListArray of books.
 * The book gets a name, description, year and edition.
 * @param filePath the path to the file containing info about the books.
	 */
public void createBooks(String filePath) throws IOException{
	String[] array = this.readToArray(filePath);
	for (String s : array) {
		String[] BookInfo =s.split(";");
		int year = Integer.parseInt(BookInfo[2]);
		int edition = Integer.parseInt(BookInfo[3]);

		Book b = new Book(BookInfo[0], BookInfo[1], year, edition);

		this.allBooks.add(b);
	}
}
//=====================================

/**
 * Finds out if a book is a course book.
 * @return true, if book is course book, otherwise false.
	 */
public boolean iscourseBook(Book b){
	boolean find = false;
	for(int i = 0; i < this.allCourses.size(); ++i){
		if(allCourses.get(i).returncourseBookName().equals(b.returnName())){
			find = true;
		}
	}

	return false;
}

/**
 * Creates a random key.
 * @return The new key.
	 */
public Key randomcreateKey(){
	Random ran = new Random();
	String keys[] = {"Key1","Key2","Key3","Key4","Key5","Key6","Key7","Key8","Key9","Key10","Key11","Key12","Key13","key14",
			"key15","key17","key18","key19", "key20"};
	int randomIndex= ran.nextInt(19);
	Key k = new Key(keys[randomIndex]);
	return k;
}

//======================================


/**
 * Finds a room from it's name.
 * @return The found room.
	 */

public Room getRoomFromName(String name){
	Room room = null;
	for(Room r : this.WorldRooms){
		if (r.getName().equals(name)){
			room= r;
		}
	}
		return room;
}

//================================================

/**
 * Reads from a file into a string array.
 * @return the string array generated from file.
	 */
public String[] readToArray(String filePath) throws IOException{
String[] LineArray = null;
try {
	ReadFile file = new ReadFile(filePath);
	LineArray = file.OpenFile();
}

	catch (IOException e) {
		System.out.println (e.getMessage());
	}
	return LineArray;
}



/**
 * Checks if a door is open or closed, return a string representation.
 * @return "Open" or "Closed"
	 */
public String isdoorOpenatRoom(String door){
	String checkdoor = null;
	if(door.equals("False")){
		checkdoor = "Open";
	}
	else{
		checkdoor ="Closed";
	}
	return checkdoor;
}


/**
 * Prints out a description of the room. 
	 */
public void roomdescription(String room){
	Room r = getRoomFromName(room);
	String [] rooms = r.getNextRooms();
	String [] openStatus = r.getDoorOpenStatus();
	System.out.println(r.getName());
	if(rooms[0].equals("X")){
		System.out.println("There is no room north of current room");
	}
	else{
		System.out.println("The room north of current room is" + rooms[0]);
	}
	if(rooms[1].equals("X")){
		System.out.println("There is no room east of current room");
	}
	else{
		System.out.println("The room east of current room is" + rooms[1]);
	}
	if(rooms[2].equals("X")){
		System.out.println("There is no room south of current room");
	}
	else{
		System.out.println("The room south of current room is" + rooms[2]);
	}
	if(rooms[3].equals("X")){
		System.out.println("There is no room west of current room");
	}
	else{
		System.out.println("The room west of current room is" + rooms[3]);
	}


	//-----------------------------------------------------------------

	if(openStatus[0].equals("X")){
		System.out.println("There is no door at the noth side of room");
	}
	else{
		System.out.println("The you find the door in north of room and it is: " + isdoorOpenatRoom(rooms[0]));
	}

	if(openStatus[1].equals("X")){
		System.out.println("There is no door at the east side of room");
	}
	else{
		System.out.println("The you find the door in east of room and it is: " + isdoorOpenatRoom(rooms[1]));
	}
	if(openStatus[2].equals("X")){
		System.out.println("There is no door at the south side of room");
	}
	else{
		System.out.println("The you find the door in south of room and it is: " + isdoorOpenatRoom(rooms[2]));
	}
	if(openStatus[3].equals("X")){
		System.out.println("There is no door at the west side of room");
	}
	else{
		System.out.println("The you find the door in west side of room and it is: " + isdoorOpenatRoom(rooms[3]));
	}


}



public static void main(String [] args) throws IOException {
String teacherFile = "../Javaparttwo/teacher.text";
String bookFile ="../Javaparttwo/book.text";
String roomFile = "../Javaparttwo/sr.text";
World w = new World();
w.createWorld(teacherFile, bookFile, roomFile);
//w.returnRandomCourse(); 
System.out.println(w.returnRandomCourse());
//System.out.println(w.randomRoom().teacher.getTeachercourse().returnQuestion());
/*
w.roomdescription("Room 5");
System.out.println(w.randomcreateKey().returnKeyName());
System.out.println(w.WorldRooms.get(0).getStudent().name);
//System.out.println(w.roomdescription("Room 5"));
System.out.println(w.WorldRooms.get(0).getKeyfromRoom().returnKeyName());
System.out.println(w.returnSfinxRoom().name);
*/
}




}
