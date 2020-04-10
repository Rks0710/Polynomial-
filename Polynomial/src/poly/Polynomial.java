package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		if(poly1 ==null) {
			return poly2;
		}
		else if(poly2==null) {
			return poly1;
		}
		else {
			Node first=null;
			Node Last=null;
			Node currentNode1=poly1;
			Node currentNode2=poly2;
			while(currentNode1!=null && currentNode2!=null) {
				//Checks if the degree is same for both polynomials
				if(currentNode1.term.degree==currentNode2.term.degree) {
					Node sum = new Node(currentNode1.term.coeff+currentNode2.term.coeff, currentNode1.term.degree, null);
					if(sum.term.coeff==0) {
						currentNode1=currentNode1.next;
						currentNode2=currentNode2.next;
					}
						else {
						if(Last!=null) { 
						Last.next=sum;
						}
						else {
						first=sum;
					}
					Last=sum;
					currentNode1=currentNode1.next;
					currentNode2=currentNode2.next;
						}
					}
				else if(currentNode1.term.degree>currentNode2.term.degree) {
				Node sum = new Node(currentNode2.term.coeff, currentNode2.term.degree, null);
				if(sum.term.coeff==0) {
					currentNode1=currentNode1.next;
					currentNode2=currentNode2.next;
				}
					else {
					if(Last!=null) {
						Last.next=sum;
					}else {
						first=sum;
					}
					Last = sum;
					currentNode2=currentNode2.next;
					}
				}
				else {
				Node sum = new Node(currentNode1.term.coeff, currentNode1.term.degree, null);
				if(sum.term.coeff==0) {
				currentNode1=currentNode1.next;
				currentNode2=currentNode2.next;
				}
				else {
					 if(Last!=null) {
							Last.next=sum;
						}else {
							first=sum;
						}
							Last = sum;
					 currentNode1=currentNode1.next;
				}
				}
				
			}
				while(currentNode1!=null) {
					Node sum = new Node(currentNode1.term.coeff, currentNode1.term.degree, null);
					 if(Last!=null) {
						 	Last.next=sum;
						}else {
							first=sum;
						}
							Last = sum;
					 currentNode1=currentNode1.next;
				}
				while(currentNode2!=null) {
					Node sum = new Node(currentNode2.term.coeff, currentNode2.term.degree, null);
					 if(Last!=null) {
							Last.next=sum;
						}else {
							first=sum;
						}
							Last = sum;
					 currentNode2=currentNode2.next;
				}
				return first;
			}
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		if(poly1 ==null) {
			return poly2;
		}
		else if(poly2==null) {
			return poly1;
		}
		else {
			Node currentNode1=poly1;
			Node currentNode2=poly2;
			
			Node c=null;
			while(currentNode1!=null) {
				while(currentNode2!=null) {				
					Node Mult=new Node(currentNode1.term.coeff*currentNode2.term.coeff,currentNode1.term.degree+currentNode2.term.degree,null);
				if(c==null) {
					c=Mult;
				}else {
					c=add(c,Mult);
					
				}
				currentNode2=currentNode2.next;
				}
				currentNode1=currentNode1.next;
				currentNode2=poly2;
				}
			return c;
		}
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		float Answer=0;
		
		while(poly!=null) {
			Answer +=  poly.term.coeff*(Math.pow(x, poly.term.degree));
			poly=poly.next;
		}
		return (float)Answer;
	}

	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
