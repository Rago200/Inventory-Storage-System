//////////////// FILE HEADER ///////////////////////////////////////////////////
//
// Title:    Inventory System Tester
// Course:   CS 300 Spring 2021
//
// Author:   Rago Senthilkumar
// Email:    rsenthilkuma@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////// FILE HEADER ///////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class test the Inventory System program.
 */
public class InventorySystemTester {
  /**
   * Tests the LinkedBox class and methods.
   *
   * @return true if they work, false otherwise.
   */
  public static boolean testLinkedBox(){
    boolean test = true;
    Box box1 = new Box(Color.BROWN);

    //Creates the LinkedBox object with a specified box.
    LinkedBox linkedBox1 = new LinkedBox(box1);

    //Check if the getNext() method returns null
    if(linkedBox1.getNext() != null){
      System.out.println("The method should have returned null.");
      test = false;
    }

    //Check if the setNext() method works.
    Box box2 = new Box(Color.BROWN);
    LinkedBox nextBox = new LinkedBox(box2);
    linkedBox1.setNext(nextBox);
    if(!linkedBox1.getNext().equals(nextBox)){
      System.out.println("The method should have returned the link to the next box.");
      test = false;
    }

    return test;
  }

  /**
   * checks for the correctness of the InventoryList.clear() method.
   * @return true if the method works, false otherwise
   */
  public static boolean testClear(){
    boolean test = true;
    InventoryList list  = new InventoryList();

    //Adds one box to the list of color yellow.
    list.addYellow(new Box(Color.YELLOW));
    list.clear();
    if(!list.isEmpty()){
      System.out.println("(1) List was not cleared.");
      test = false;
    }

    //Adds three boxes of different colors;
    list.addYellow(new Box(Color.YELLOW));
    list.addBlue(new Box(Color.BLUE));
    list.addBrown(new Box(Color.BROWN));
    list.clear();
    if(!list.isEmpty()){
      System.out.println("(2) List was not cleared");
      test = false;
    }

    return test;
  }
  // checks for the correctness of the InventoryList.addYellow(),
// InventoryList.addBlue(), and InventoryList.addBrown() methods

  /**
   * Checks for the correctness of the InventoryList.addYellow(),
   * InventoryList.addBlue(), and InventoryList.addBrown() methods.
   *
   * @return true if the methods work false otherwise
   */
  public static boolean testAddBoxes(){
    boolean test = true;
    InventoryList list1 = new InventoryList();

    //Adding Blue, Yellow and Brown into empty list.
    list1.addBlue(new Box(Color.BLUE));
    if(list1.get(0).getColor() != Color.BLUE || list1.getBlueCount() != 1){
      System.out.println("The list did not add the blue box to the empty list.");
      test = false;
    }
    list1.clear();
    list1.addYellow(new Box(Color.YELLOW));
    if(list1.get(0).getColor() != Color.YELLOW || list1.getYellowCount() != 1 ){
      System.out.println("The list did not add the yellow box to the empty list.");
      test = false;
    }
    list1.clear();
    list1.addBrown(new Box(Color.BROWN));
    if(list1.get(0).getColor() != Color.BROWN || list1.getBrownCount() != 1){
      System.out.println("The list did not add the brown box to the empty list.");
      test = false;
    }
    list1.clear();

    //Adding scenario with multiple different colored boxes.
    Box.restartNextInventoryNumber();
    list1.addYellow(new Box(Color.YELLOW));
    list1.addBlue(new Box(Color.BLUE));
    list1.addBrown(new Box(Color.BROWN));
    list1.addBlue(new Box(Color.BLUE));
    list1.addBlue(new Box(Color.BLUE));
    if(list1.get(0).getInventoryNumber() != 1 || list1.get(1).getInventoryNumber() != 5
            || list1.get(2).getInventoryNumber() != 4|| list1.get(3).getInventoryNumber() != 2
            || list1.get(4).getInventoryNumber() != 3){
      System.out.println("The order was incorrect when the boxes were added.");
      test = false;
    }
    if(list1.size() != 5 || list1.getYellowCount() != 1 || list1.getBlueCount() != 3
            || list1.getBrownCount() != 1){
      System.out.println("The sizes for the boxes and list may not have been incremented correctly.");
      test = false;
    }

    return test;
  }

  /**
   * Checks for the correctness of the InventoryList.removeBox()
   * InventoryList.removeYellow(), and InventoryList.remove Brown()
   * methods
   *
   * @return true if the methods work, otherwise false
   */
 public static boolean testRemoveBoxes(){
    boolean test = true;
    InventoryList list2 = new InventoryList();

    //Removing the box of both colors for an empty list.
   try {
     list2.removeYellow();
   }catch (Exception e){
     System.out.println("The exception was caught successfully for removeYellow()");
   }

   try {
     list2.removeBrown();
   }catch (Exception e){
     System.out.println("The exception was caught successfully for removeBrown()");
   }
   list2.clear();
   Box.restartNextInventoryNumber();

   //Removing box of Yellow and Brown with multiple boxes.
   list2.addBrown(new Box(Color.BROWN));
   list2.addBlue(new Box(Color.BLUE));
   list2.addYellow(new Box(Color.YELLOW));
   list2.addBlue(new Box(Color.BLUE));
   list2.addBrown(new Box(Color.BROWN));

   list2.removeBrown();
   list2.removeYellow();

   if(list2.get(0).getInventoryNumber() != 4 || list2.get(1).getInventoryNumber() != 2
           || list2.get(2).getInventoryNumber() != 1){
     System.out.println("The remove method for yellow and brown may have issues cause " +
             "the order of the list is wrong.");
     System.out.println(list2.toString());
     test = false;
   }

   if(list2.size() != 3 || list2.getBrownCount() != 1 || list2.getYellowCount() != 0){
     System.out.println("The remove methods for the color might not have updated the sizes and counts.");
     test = false;
   }
   list2.clear();
   Box.restartNextInventoryNumber();

   //Testing the remove box with no boxes.
   try {
     list2.removeBox(1);
   }catch (Exception e){
     System.out.println("Exception was caught successfully for RemoveBox()");
   }

   //Removing box with multiple boxes in the list.
   list2.addBrown(new Box(Color.BROWN));
   list2.addBlue(new Box(Color.BLUE));
   list2.addYellow(new Box(Color.YELLOW));
   list2.addBlue(new Box(Color.BLUE));
   list2.addBrown(new Box(Color.BROWN));

   list2.removeBox(3);
   list2.removeBox(5);


   if(list2.get(0).getInventoryNumber() != 4 || list2.get(1).getInventoryNumber() != 2
           || list2.get(2).getInventoryNumber() != 1) {
     System.out.println("The remove box method may not be working properly");
     test = false;
   }

   if(list2.size() != 3 || list2.getBrownCount() != 1 || list2.getYellowCount() != 0){
     System.out.println("The removeBox() might not have updated the sizes and counts.");
     test = false;
   }

   return test;
 }

  /**
   * Checks for the correctness of the InventoryList.get() method
   *
   * @return true if the get() method works, false otherwise.
   */
  public static boolean testGetBoxes(){
   boolean test = true;
   InventoryList list3 = new InventoryList();
   Box.restartNextInventoryNumber();

   list3.addBrown(new Box(Color.BROWN));
   list3.addYellow(new Box(Color.YELLOW));
   list3.addBlue(new Box(Color.BLUE));

   //Testing for a invalid input
    try{
      list3.get(3);
      test = false;
    }catch (Exception e){
      System.out.println("The exception was caught successfully for get()");
      test = true;
    }

    //Testing a valid input
    if(list3.get(2).getInventoryNumber() != 1){
      System.out.println("The get() method did work properly.");
      test = false;
    }

    return test;
  }
  // a test suite method to run all your test methods

  /**
   * A test suite method to run all test methods.
   *
   * @return true if all tests work, false otherwise.
   */
  public static boolean runAllTests(){
    if(testClear() && testAddBoxes() && testClear() && testRemoveBoxes() && testGetBoxes()){
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println("runAllTests(): " + runAllTests());
    //demo();
  }

  /**
   * Helper method to display the size and the count of different boxes stored in a list of boxes
   * @param list a reference to an InventoryList object
   * @throws NullPointerException if list is null
   */
  private static void displaySizeCounts(InventoryList list) {
    System.out.println("  Size: " + list.size() +
            ", yellowCount: " + list.getYellowCount() +
            ", blueCount: " + list.getBlueCount() +
            ", brownCount: " + list.getBrownCount());
  }

  /**
   * Demo method showing how to use the implemented classes in P07 Inventory Storage System
   */
  public static void demo() {
    // Create a new empty InventoryList object
    InventoryList list = new InventoryList();
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add a blue box to an empty list
    list.addBlue(new Box(Color.BLUE)); // adds BLUE 1
    System.out.println(list); // prints list's content
    list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 2 at the head of the list
    System.out.println(list); // prints list's content
    list.addBlue(new Box(Color.BLUE)); // adds BLUE 3
    System.out.println(list); // prints list's content
    list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 4
    System.out.println(list); // prints list's content
    list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 5 at the head of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add more boxes to list and display its contents
    list.addBrown(new Box(Color.BROWN)); // adds  BROWN 6 at the end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.addBrown(new Box(Color.BROWN)); // adds BROWN 7 at the end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBrown(); // removes BROWN 7 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.addBlue(new Box(Color.BLUE)); // adds BLUE 8
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBrown(); // removes BROWN 6 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeYellow(); // removes YELLOW 5
    System.out.println(list); // prints list's content
    list.removeBox(3); // removes BLUE 3 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    try {
      list.removeBox(25); // tries to remove box #25
    }
    catch(NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // remove all yellow boxes
    while(list.getYellowCount() != 0) {
      list.removeYellow();
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBox(1); // removes BLUE 1 from the list -> empty list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.addBrown(new Box(Color.BROWN)); // adds BROWN 9 to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBox(8); // removes BLUE 8 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBrown(); // removes BROWN 9 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 10 to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBox(10); // removes YELLOW 10 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
  }
}
