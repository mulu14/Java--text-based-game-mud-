
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;



/* a class where Avatar object and method are defined
 * finished & unfinished course, bag, room and world are the fields of the class

 */

public class Avatar {
	ArrayList<Course> finishedCourse; // array of finished course
	ArrayList<Course> unfinishedCourse; // array of unfinished courseS
	Bag inventory; // Bag object
	Room currentRoom; // Room object
	World theWorld;  // World object


	//public enum Direction {NORTH, SOUTH, EAST, WEST};
	
	public Avatar(){
		
	}

//===============================================
//Constructor

	/* a class constructor, where it take two parameters and create new objects for finished & unfinished course and bag
	 * @param startRoom
	 * @param world

	 */
	
	
	
	public Avatar(Room startRoom, World world) throws IOException{
		this.finishedCourse = new ArrayList<Course>();
		this.unfinishedCourse = new ArrayList<Course>(0);
		this.inventory = new Bag();
		this.currentRoom= startRoom;
		this.theWorld= world;
	}

//==========================================
	/** return the current room where Avatar is found
	  */
	public Room returnCurrentRoom(){
		return this.currentRoom;
	}

	/** set the current room where Avatar is found to new room
	 * @param room the room that the current room will bw set to.
	  */

	public void setNewRoom(Room room){
		this.currentRoom = room;
	}
	/** Return inventory from bag
		*@return the bag that is inventory.
	  */
	public Bag getInventory(){
		return this.inventory;
	}

//=================================================

		/**	@return the list of finished course array
	  */
	public ArrayList<Course> returnunfinishedCourse(){
		return this.unfinishedCourse;
	}
	/** @return the list of unfinished course array
	  */

	public ArrayList<Course> returnfinishedCourse(){
		return this.finishedCourse;
	}

//=================================================

	/** change the direction of the room, if the room is open state the name of the room
	 * while changing direction, if the room is closed, state the door is locked
	 * @param  dir the direction of the room we want to change to
	 * */

	public void changeRoom(Room.Direction dir) throws IOException{
		String roomName = this.currentRoom.getDirection(dir);
			if (!(roomName.equals("X"))){
				if (currentRoom.isOpen(dir)){
					this.currentRoom = this.theWorld.getRoomFromName(roomName);
				}
				else {
					System.out.println("The door is locked."); // we need to use key here
				}
			}
			else {
				System.out.println("There is no room in that direction.");
			}
	}


//

	/** change the direction of the room, if the room is open state the name of the room
	 * while changing direction, if the room is closed, state the door is locked
	 * @param dir the direction of the door we want to unlock
	 * */
public void unlockDoor(Room.Direction dir){
	if(this.inventory.isKey()){
		String roomname = currentRoom.getDirection(dir);
		Room r = this.theWorld.getRoomFromName(roomname);
		this.currentRoom.unlockDoor(dir, r);
		this.inventory.dropAnyKey();
		System.out.println("\nYou successfully unlocked the door!");

	}
	else {
		System.out.println("\nYou have no key.");

	}
}
//=====================================================

/* check the inventory is instance of key, then return the key
 *
 I am not sure we need this function //S
 * */

	public String useKey(Key k){
		for(int i = 0; i < inventory.size; ++i){
			if(inventory.returnIndexitem(i) instanceof Key){
				return (String) inventory.returnIndexitem(i);
			}

		}
		return null;
	}

//=================================================================
	/** To check the course exist on unfinished course list;
	 * @param course the course we want to check
	 */

	 public boolean isCourseExistedInunFinishedCourse(Course course){
	 boolean found = false;
	 for(int i = 0; i < unfinishedCourse.size(); ++i){
		 if(unfinishedCourse.get(i).returnCourseName().equals(course.returnCourseName())){
			 found = true;
			 return found;
		 }
	 }
	 return false;
 }
	 /** make conversation with student
		 * @param a the avatar
		 * @param s the student
		 */

	 public void  talkTootherStudent(Avatar a, Student s ){
		 assert(s != null);
		 System.out.println("Hej my name is " + s.getName() + ", I finished " + s.returnFinshedcourse().returnCourseName() + " and still not finnished " + s.returnunFinshedcourse().returnCourseName() + ".");
		 System.out.println("You: I have finished " + a.finishedCourse.size() + " courses!");


	 }
    /** To check the course exist on unfinished course list;
	 * @param course the course we want to check
	 */

	 public boolean isCourseExistedInFinishedCourse(Course course){
	 boolean found = false;

	 for(int i = 0; i < finishedCourse.size(); ++i){
		 if(finishedCourse.get(i).returnCourseName().equals(course.returnCourseName())){
			 found = true;
			 return found;
		 }
	 }
	 return false;
 }

//===========================================================================
	 /** return the course if it existed in the finished course list else return null
		 * @param course the course we want to check
		 */

 public Course returnFinishedCourse(Course course){
				 Course c = null;
		for(int i = 0; i < finishedCourse.size(); ++i){
			if(finishedCourse.get(i).returnCourseName().equals(course.returnCourseName())){
				c = finishedCourse.get(i);
				return c;
			}
		}
		return c;
	}

 /** return the course if it existed in the unfinished course list else return null
	 * @param course the course we want to check
	 */
	public Course returnunFinishedCourse(Course course){
		 Course c = null;
		for(int i = 0; i < unfinishedCourse.size(); ++i){
			if(unfinishedCourse.get(i).returnCourseName().equals(course.returnCourseName())){
				c =  unfinishedCourse.get(i);
				return c;
			}
		}
		return c;
	}

//========================================================

	/** Find the index of finished course
	 *@param course the course we want to get the idex of
	 * */

	public int finishedcourseIndex(Course course){
	 int j = 0;
	for(int i = 0; i < finishedCourse.size(); ++i){
		if(finishedCourse.get(i).returnCourseName().equals(course.returnCourseName())){
				 j = i;
			return j;
		}
	}
	return j;
 }

	/**Find the index of unfinished course
	 *@param course the course we want to find the index of.
	 * */

	public int unfinishedcourseIndex(Course course){
	 int j = 0;
	for(int i = 0; i < finishedCourse.size(); ++i){
		if(finishedCourse.get(i).returnCourseName().equals(course.returnCourseName())){
				 j = i;
			return j;
		}
	}
	return j;
 }
//================================================


	/**Register to course
	 * It is only register if the course is not existed in finished and unfinished course list
	 *@param course the course the avatar registers to
	 * */


	public void registerTocourse(Course c){
		if(!isCourseExistedInFinishedCourse(c)){
			if(!isCourseExistedInunFinishedCourse(c)){
				this.unfinishedCourse.add(c);
			}
		}

		else{
			System.out.println("You are already register for the course");
		}

	}

//=================================================

	/**Avatar interact with student
	 * the student has finished course where course is in Avatar unfinished course and
	 * the student has unfinished course where course in Avatar finished course list, they trade book
	 * the course does not match, they don't trade anything
	 * the student has no book to trade, still get the book from student by giving the right answer to question
	 * It is only register if the course is not existed in finished and unfinished course list
	 *@param a the avatar
	 *@param s the student
	 * */


	public void interactWithStudent(Avatar a, Student s){

		if(isCourseExistedInFinishedCourse(s.returnunFinshedcourse())){
			if (isCourseExistedInunFinishedCourse(s.returnFinshedcourse())) {
				int Index = finishedcourseIndex(s.returnunFinshedcourse());
				Book avatarBook = this.finishedCourse.get(Index).returncourseBook();
				int IndexforstudentBook = unfinishedcourseIndex(s.returnFinshedcourse());
				Book studentBook = this.unfinishedCourse.get(IndexforstudentBook).returncourseBook();
				System.out.println("Trade with :" + s.name + " "+ avatarBook.returnName()+ " "+" I get: " + studentBook.returnName());
				this.inventory.dropObject(avatarBook);
				this.inventory.addObject(studentBook);
			}
			else {
				Course course = theWorld.returnCourseFromQuestion(theWorld.returnRandomCourse().returnQuestion());
				String answer = course.returnAnswer();
				System.out.println("I got answer as exchange"  + answer);
			}


		}
		else if(!(isCourseExistedInFinishedCourse(s.returnunFinshedcourse()))){
			System.out.println("You don't have any book that the student needs.");

		}


	}

//=======================================================================================
	/**
	* prints out the question and the answer options, and returns the player answer
	* @param q the question
	* @param a1 wrong answer
	* @param a2 wrong answer
	* @param ans Correct answer
	* @return the player answer
* */
private String printOptions(String q, String a1, String a2, String ans){
	System.out.println(q);
	System.out.println("1. " + a1);
	System.out.println("2. " + a2);
	System.out.println("3. " + ans);
	System.out.println("The answer for this question is:");
	Scanner scan = new Scanner(System.in);
	String studentRespond = scan.nextLine();
	return studentRespond;
}


	/**
	 * When the teacher teaches a course that you have finnished, you might get a question.
	 * If you answer correctly, the course stayes in finnished courses.
	 * If your answer is wrong, the course moves back into unfinnished courses.
	 * @param q the question
	 * @param a1 wrong answer
	 * @param a2 wrong answer
	 * @param ans Correct answer
	 * @param c the course
	 * */

private void qFinishedCourse(String q, String a1, String a2, String ans, Course c){

	String studentRespond= this.printOptions(q, a1, a2, ans);
	if(!studentRespond.equals(ans)){
		System.out.println("The answer is wrong, your Hp deducted by:" + c.returnHp() + "  " +  "course move to unfinished list, Sorry!");
	finishedCourse.remove(c);
	unfinishedCourse.add(c);
	}
	else {
			System.out.println("Correct. Move along");
			}
		}

		/**
		 * When the teacher teaches a course that you have not finnished, you might get a question.
		 * If you answer correctly, the course moves to finnished course2rses
		 * @param q the question
		 * @param a1 wrong answer
		 * @param a2 wrong answer
		 * @param ans Correct answer
		 * @param c the course
		 * */
private void qUnFinishedCourse(String q, String a1, String a2, String ans, Course c){

	String studentRespond= this.printOptions(q, a1, a2, ans);
	if(studentRespond.equals(ans)){
		System.out.println("The answer is correct, your Hp increase by:" + c.returnHp() + "  " +  "course move to finished list, Congra");
		finishedCourse.add(c);
		unfinishedCourse.remove(c);
	}
	else {
			System.out.println("Sorry, wrong answer");
			}
		}

	/**
	 * Get question from teacher;
	 * the question could be finished or unfinished course
	 * depends on answer, the course would move to unfinished or finished course list

	 * */
public void getQuestionfromTeacher(){
	Teacher t = this.currentRoom.getTeacher();
	if(!(t.getTeachername().equals("X"))){
	Course course = t.getTeachercourse();
	String question = course.returnQuestion();
	String alternativeOne = course.returnAlternativeOne();
	String alternativeTwo = course.returnAlternativeTwo();
	String answer = course.returnAnswer();
	Random a= new Random();

	if(this.isCourseExistedInFinishedCourse(course)){
		if(a.nextInt(2)<1){
			this.qFinishedCourse(question, alternativeOne, alternativeTwo, answer, course);
		}
		else {
			System.out.println("No question today!");
	  	}
		}

	else if(this.isCourseExistedInunFinishedCourse(course)){
			if(a.nextInt(4)<3){
				this.qUnFinishedCourse(question, alternativeOne, alternativeTwo, answer, course);
				}
		 else {
				System.out.println("No question today!");
			}
		}

	else {
		if(!this.isCourseExistedInFinishedCourse(course) && !this.isCourseExistedInunFinishedCourse(course)){
			System.out.println("You enroll: " + course.returnCourseName());
			unfinishedCourse.add(course);
	  	}
	 }
	}
	else {
		System.out.println("There is no teacher in this room"); 
	}
}




//===================================================

	/** Get the total HP
	 * @return the total HP of finished student course
	 * */
	public int calcualteHp(){
		int Hp = 0;
		for(Course c : finishedCourse){
			Hp += c.returnHp();
		}
		return Hp;
	}

	/**
	 * check if the total HP is equal to 180
	 * @return true if HP equals 180
	 * */

	public boolean finishedCourseHptotal(){
		if(calcualteHp() == 180){
			return true;
		}
		return false;
	}
	/*
	 * print list of finished course
	 * */

	public void printCourse(){
		for(Course c: this.finishedCourse){
			System.out.println(c.returnCourseName());
		}
	}
	/*
	 * print list of unfinished course
	 * */
	public void printUnfinishedCourse(){
		for(Course c: this.unfinishedCourse){
			System.out.println(c.returnCourseName());
		}
	}

	/*
	 * print out the number of finished/unfinished courses, and which ones they are
	 */
	public void printAllCourseInfo(){
		int fin =  this.finishedCourse.size();
		int un = this.unfinishedCourse.size();
		System.out.println("\nYou have finnished " + fin + " courses. ");
		 if (fin >0){
			 System.out.println("They are:\n");
			 this.printCourse();
		 }
		System.out.println("\nYou have " + un + " unfinished courses. ");
		if (un >0){
			System.out.println("They are:\n");
			this.printUnfinishedCourse();
		}


	}


	/*
	 * Get graduate if the Avatatr full fill the requirement
	 * */

	public void graduate(){
		if (this.currentRoom.hasSfinxen()){
			if (this.finishedCourseHptotal()){
			System.out.println("Congratulations!");
			this.printCourse();
			}
			else {
				System.out.println("You have to take more HP!");

			}
		}
		else {
			System.out.println("You can not graduate in this room.");

		}
	}

	public static void main(String [] args)  throws IOException{
		String teacherFile = "../Javaparttwo/teacher.text";
		String bookFile ="../Javaparttwo/book.text";
		String roomFile = "../Javaparttwo/sr.text";
		World w = new World();
		w.createWorld(teacherFile, bookFile, roomFile);
		Room r = w.getRoomFromName("Room 1246");
		Avatar a = new Avatar(r,w);
		//System.out.println(a.calcualteHp());
		a.getQuestionfromTeacher();


		Course course1 = w.returnRandomCourse();
		Course course2 = w.returnRandomCourse();
		Course course3 = w.returnRandomCourse();
		Course course4= w.returnRandomCourse();
		Course course5 = w.returnRandomCourse();
		Course course6 = w.returnRandomCourse();
		String s = w.createRandomStudent().name;
		Student student =  new Student(s, course6, course1);

		a.changeRoom(Room.Direction.NORTH);
		a.changeRoom(Room.Direction.EAST);
		a.changeRoom(Room.Direction.SOUTH);
		a.changeRoom(Room.Direction.WEST);

		System.out.println(a.inventory);
		System.out.println("Room = " + a.returnCurrentRoom().getName());
/*
		a.registerTocourse(course1);
		a.registerTocourse(course2);
		a.registerTocourse(course3);
		a.registerTocourse(course4);
		a.registerTocourse(course1);
		System.out.println(a.finishedCourse.size());
		System.out.println(a.unfinishedCourse.size());
		a.printCourse();
		a.printUnfinishedCourse();
		*/


	}


}
