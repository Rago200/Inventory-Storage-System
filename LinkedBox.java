//////////////// FILE HEADER ///////////////////////////////////////////////////
//
// Title:    Linked Box
// Course:   CS 300 Spring 2021
//
// Author:   Rago Senthilkumar
// Email:    rsenthilkuma@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////// FILE HEADER ///////////////////////////////////////////////////
/**
 * The class creates LinkedBox objects that represents a linked box node.
 *
 */
public class LinkedBox {

  private Box box; //The data carried by this linked box node.
  private LinkedBox next; //the link to the next linkedBox.

  /**
   * Creates a new LinkedBox with a specified box and null as next field.
   * @param box the data carried by the linked box node.
   */
  public LinkedBox(Box box){
    this.box = box;
    next = null;
  }

  /**
   * Creates a new LinkedBox node with given box and next fields.
   *
   * @param box the data carried by the linked box node.
   * @param next the link to the next linked box.
   */
  public LinkedBox(Box box, LinkedBox next){
    this.box = box;
    this.next = next;
  }

  /**
   * Returns the data field box.
   *
   * @return the data field box
   */
  public Box getBox() {
    return this.box;
  }

  /**
   * Returns the data field box.
   *
   * @return the next linked box node.
   */
  public LinkedBox getNext() {
    return this.next;
  }

  /**
   * Sets the link to the next linked box node.
   *
   * @param next the link to the next linked box.
   */
  public void setNext(LinkedBox next) {
    this.next = next;
  }

  /**
   * Returns a Sting representation of this Linked box.
   *
   * @return a String representation of this Linked box.
   */
  @Override
  public String toString(){
    if(next != null){
      return this.box.toString() + " -> ";
    }else {
      return this.box.toString() + " -> END";
    }
  }
}
