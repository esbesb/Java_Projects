/**
 * Course:      Data Structures and Algorithms for Language Processing SS 2017
 * Assignment:  lab 3, exercise 2. 12.05.2017
 * Author:      Timothy Day
 * Description: This program takes a string as an argument and checks it for XML wellformedness
 *
 * Honor Code:  I pledge that this program represents my own work.
 *		I received help from:
 * 		no one
 *		in designing and debugging my program.
 */

import java.util.Stack;

public class XMLChecker {
	/**
	 * wellFormedXML checks a String for the XML requirements for wellformedness
	 * @param xml - a string
	 * @return true if well formed XML
	 */
	public boolean wellformedXML(String xml) {
		
		boolean isWellFormed = true;
		Stack<String> tagStorage = new Stack<String>(); //for storing opening tags
		String[] line = xml.split(">");

		// check for empty string input
		if (xml.equals("")){
			return false;
		}
		
		//check that first token is a valid opening tag. 
		if (line[0].charAt(0) != '<' || line[0].substring(0, line[0].length()-1).contains("/")) { 
			return false;
		} else if (line[0].charAt(line[0].length()-1) == '/' && line.length > 1) { //if last char is '/' (empty content single tag), and there is more to come
			return false;
		}
		
		//make sure that first tag matches the last (but only if they are not the same tag!)
		String rootStart = line[0].substring(1); //trim the leading '<'
		String rootEnd = line[line.length-1];
		
		//check that the last tag is valid. starts with a </
		if (rootEnd.charAt(0) != '<') {
			return false;
		}
		
		//make sure rootStart and rootEnd match
		rootEnd = rootEnd.substring(line[line.length-1].indexOf('<')).substring(2); //trim the leading "</"
		if (!rootStart.equals(rootEnd) && line.length > 1) { //if mor than 1 tag, and the start/end not equal...
			isWellFormed = false;
		}
		
		//check all tags are nested correctly
		
		if (isWellFormed) { //if already false, save processing
			String tagPortion, openTag, closeTag = "";
			
			for (int i=0; i<line.length; i++) { //go through each token
				
				//get the tag part of each token, ignoring content
				tagPortion = line[i].substring(line[i].indexOf('<')); 
				if (tagPortion.indexOf(' ') > 0){ //if the tag has whitespace in it, it is a bad tag
					isWellFormed = false;
				}
				
				if (line[i].contains("<")) { //it should, as long as no typos
					
					if (tagPortion.indexOf('/') < 0) { 					//opening tag
						tagStorage.push(tagPortion); //add to stack
						
					} else if (tagPortion.charAt(tagPortion.length()-1) == '/') {   	//single empty content tag
						//do nothing
					} else if (tagPortion.charAt(1) == '/') {				//closing tag
						
						//make sure we aren't closing the root early. if we are not at the last tag and this tag is same as root
						if (i != line.length-1 && rootStart.equals(tagPortion.substring(2))) { //not at last tag yet, but get early root close
							return false;
						}
						
						if (!tagStorage.empty()) { 			 //check stack. was the previous tag a matching opener?
							closeTag = tagPortion.substring(2); 	 //trim first two chars to get the tag name
							openTag = tagStorage.pop().substring(1); //get previous open tag, trim the leading '<'
							if (!openTag.equals(closeTag)) { 	 //if they don't match, false. XML is case sensitive
								isWellFormed = false;
							}
						} else { 					 // no open tag on stack to match the close tag with
							isWellFormed = false;
						}
					}	
				} else { // if no '<' in this token, something was typed incorrectly
					isWellFormed = false;
				}
			} //end for loop
			
			if (!tagStorage.empty()) { //should be empty if all the opening tags are accounted for
				isWellFormed = false;
			}
		}
		return isWellFormed;
	}
// 	public static void main(String[] args) {
// 		XMLChecker sample = new XMLChecker();
// 		System.out.println(sample.wellformedXML("</a>"));
// 	}
}

