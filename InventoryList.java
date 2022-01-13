//////////////// FILE HEADER ///////////////////////////////////////////////////
//
// Title:    Inventory List
// Course:   CS 300 Spring 2021
//
// Author:   Rago Senthilkumar
// Email:    rsenthilkuma@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////// FILE HEADER ///////////////////////////////////////////////////
import java.util.NoSuchElementException;

/**
 * This class models the singly linked list data structure that stores elements of type Box.
 */
public class InventoryList {

  private LinkedBox head; //represents the head of this list.
  private LinkedBox tail; //represents the tail of this list.
  private int size; //the total number of boxes stored in this list.
  private int yellowCount; //the total number of yellow boxes in this list.
  private int blueCount; //the total number of blue boxes in this list.
  private int brownCount; //the total number of brown boxes in this list.

  /**
   * Return the size of this list.
   *
   * @returnt the size of this LinkedBoxList
   */
  public int size() {
    return this.size;
  }

  /**
   * Checks whether this LinkedBoxList is empty.
   *
   * @return true if this LinkedBoxList is empty, false otherwise.
   */
  public boolean isEmpty() {
    if (this.size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Removes all of the elements from the list. The list will be empty after this call returns.
   */
  public void clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.blueCount = 0;
    this.brownCount = 0;
    this.yellowCount = 0;
  }

  /**
   * Adds a brown box at the end of this inventory list
   *
   * @param brownBox new brown box to be added to this list
   */
  public void addBrown(Box brownBox) {
    if (brownBox == null || brownBox.getColor() != Color.BROWN) {
      throw new IllegalArgumentException("The Box provided was not brown or was null.");
    }

    LinkedBox linkedBoxNew = new LinkedBox(brownBox);
    if (head == null) {
      this.head = linkedBoxNew;
      this.tail = linkedBoxNew;
    } else {
      tail.setNext(linkedBoxNew);
      this.tail = linkedBoxNew;
    }

    size++;
    brownCount++;
  }

  /**
   * Adds a new yellow box at the head of this list.
   *
   * @param yellowBox new box to be added to this list.
   */
  public void addYellow(Box yellowBox) {
    if (yellowBox == null || yellowBox.getColor() != Color.YELLOW) {
      throw new IllegalArgumentException("The Box was not yellow or was null.");
    }

    LinkedBox linkedBoxNew = new LinkedBox(yellowBox);
    if (head == null) {
      this.head = linkedBoxNew;
      this.tail = linkedBoxNew;
    } else {
      linkedBoxNew.setNext(this.head);
      this.head = linkedBoxNew;
    }

    size++;
    yellowCount++;
  }

  /**
   * Adds a new blue box at the top of blue boxes if the list contains any blue box.
   * Blue boxes must be added at the buttom of yellow boxes and at the top of all
   * the brown boxes if any.
   *
   * @param blueBox new box to be added to this list.
   */
  public void addBlue(Box blueBox) {
    if (blueBox == null || blueBox.getColor() != Color.BLUE) {
      throw new IllegalArgumentException("The Box was not blue or was null");
    }

    LinkedBox newLinkedBox = new LinkedBox(blueBox);
    if (isEmpty()) {
      this.head = newLinkedBox;
      this.tail = newLinkedBox;
    } else if (brownCount == 0 && blueCount == 0) {
      tail.setNext(newLinkedBox);
      this.tail = newLinkedBox;
    } else if (yellowCount == 0) {
      newLinkedBox.setNext(this.head);
      this.head = newLinkedBox;
    } else {
      LinkedBox current = head;
      LinkedBox previous = null;
      for(int i = 0; i < yellowCount; i++){
        previous = current;
        current = current.getNext();
      }
      previous.setNext(newLinkedBox);
      newLinkedBox.setNext(current);
    }

    size++;
    blueCount++;
  }

  /**
   * Returns the element stored at postion index of this list without removing it.
   *
   * @param index position within this list
   * @returnthe box stored at position index of this list
   */
  public Box get(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("The index is less than 0 or is greater " +
              "than or equal to the size.");
    }

    int count = 0;
    LinkedBox current = head;
    while (current.getNext() != null && count < index) {
      count++;
      current = current.getNext();
    }

    return current.getBox();
  }

  /**
   * Removes and returns the box at the head of this list if its color is yellow.
   *
   * @return
   */
  public Box removeYellow() {
    if (yellowCount == 0) {
      throw new NoSuchElementException("There are no yellow boxes in the list.");
    }

    LinkedBox removeBox = head;
    head = head.getNext();

    yellowCount--;
    size--;
    return  removeBox.getBox();
  }

  /**
   * Removes and returns the element at the tail of this list if it has a brown color.
   *
   * @return a reference to the removed element.
   */
  public Box removeBrown(){
    if(brownCount == 0){
      throw new NoSuchElementException("There are no yellow boxes in the list.");
    }

    if(size == 1){
     LinkedBox removeBox = head;
     head = null;
     tail = null;
     size--;
     brownCount--;
     return removeBox.getBox();
    }

    LinkedBox previous = null;
    LinkedBox current = head;
    while(current.getNext() != null){
      previous = current;
      current = current.getNext();
    }
    previous.setNext(null);
    tail = previous;

    size--;
    brownCount--;
    return current.getBox();
  }

  /**
   * Removes and returns a box given its inventory number from this list.
   *
   * @param inventoryNumber inventory number of the box to be removed from this list.
   * @return a reference to the removed element.
   */
  public Box removeBox(int inventoryNumber){
    if(isEmpty()){
      throw  new IllegalArgumentException("No box has the inventory number or list is empty.");
    }

    LinkedBox current = head;
    LinkedBox previous = null;
    Box inventoryBox = null;
    while(current != null) {
      if(current.getBox().getInventoryNumber() == inventoryNumber){
        if(size == 1){
          inventoryBox = head.getBox();
          head = null;
          tail = null;
        }
        else if(current == this.head){
          inventoryBox = head.getBox();
          head = head.getNext();
        }
        else if(current == tail){
          inventoryBox = tail.getBox();
          tail = previous;
          tail.setNext(null);
        }
        else{
          inventoryBox = current.getBox();
          previous.setNext(current.getNext());
        }
      }
      previous = current;
      current = current.getNext();
    }

    if(inventoryBox == null){
      throw new NoSuchElementException("The box with that inventory number was not found.");
    }

    if(inventoryBox.getColor() == Color.BLUE){
      blueCount--;
    }
    else if(inventoryBox.getColor() == Color.BROWN){
     brownCount--;
    }
    else if(inventoryBox.getColor() == Color.YELLOW){
      yellowCount--;
    }

    size--;

    return inventoryBox;
  }

  /**
   * Returns the number of brown boxes stored in this list.
   *
   * @return the brownCount.
   */
  public int getBrownCount(){
    return this.brownCount;
  }

  /**
   * Returns the number of yell boxes stored in this list
   * @return the yellow count
   */
  public int getYellowCount(){
    return this.yellowCount;
  }

  /**
   * Returns the number of blue boxes stored in this list.
   *
   * @return the blueCount
   */
  public int getBlueCount(){
    return this.blueCount;
  }


  /**
   * Returns a String representation of the contents of this list.
   * If this list is empty, an empty String"" will be returned.
   *
   * @returnreturn a String representation of the content of this list.
   * If this list is empty, an empty String ("") will be returned.
   */
  @Override
  public String toString() {
    if(isEmpty()){
      return "";
    }

    String list = "";
    LinkedBox current = head;
    while(current != null){
      list = list + current.toString();
      current = current.getNext();
    }

    return list;
  }
}
