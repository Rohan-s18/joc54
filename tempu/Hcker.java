import java.util.*;

public class Hcker {
	
	private class SinglyLinkedListNode{
        private SinglyLinkedListNode next;
        private int data;
        private SinglyLinkedListNode(int data){
            this.data = data;
        }
    }
		
	public static void main(String[] args) {
		//rotCheck();
		System.out.println("User Password: " + "\t");
		String password = new Scanner(System.in).next();
		String encryptedPassword = encryptPassword(password);
		System.out.println("Encrypted Password:" + "\t" + encryptedPassword);
		String decryptedPassword = decryptPassword(encryptedPassword);
		System.out.println("Decrypted Password:" + "\t" + decryptedPassword);
		//po();
	}
	
	public static void po() {
	SinglyLinkedListNode head = null;
    Scanner sc = new Scanner(System.in);
    int i = sc.nextInt();
    Hcker s = new Hcker();
    for(int j = 0; j < i; j++){
        int temp = sc.nextInt();
        head = s.insertNodeAtHead(head,temp);
    }
    SinglyLinkedListNode trav = head;
    while(trav != null){
        System.out.println(trav.data);
        trav = trav.next;
    }
}

	public SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode llist, int data){
		SinglyLinkedListNode temp = new SinglyLinkedListNode(data);
		temp.next = llist;
		llist = temp;
		return llist;
	}
	

	
	public static void go() {
		LList<Integer> myList = new LList<Integer>();
		myList.add(0);
		myList.add(3);
		myList.add(4);
		myList.add(5);
		myList.add(1,1);
		myList.add(2,2);
		myList.printList();
		Iterator<Integer> iter = myList.iterator();
		System.out.println("\n");
		while(iter.hasNext()) 
			System.out.print(iter.next());
	}
	
	public static List<Integer> leftRotation(int d, List<Integer> arr) {
		int[] arry = new int[arr.size()];
		for(int i = 0; i < arr.size(); i++) {
			arry[i] = arr.get(i);
		}
		
		for(int i = 0; i < d; i++) {
			int head = arr.get(0);
			for(int j = 1; j < arr.size(); j++) {
				arr.set(j-1, arr.get(j));
			}
			arr.set(arr.size()-1, head);
		}
		
		return arr;
	}
	
	public static List<Integer> leftRotation1(int d, List<Integer> arr) {
		int[] arry = new int[arr.size()];
		for(int i = 0; i < arr.size(); i++) {
			arry[i] = arr.get(i);
		}
		
		for(int i = 0; i < arr.size(); i++) {
			if(i < d) {
				int neg = i - d;
				arr.set(arr.size() + neg, arry[i]);
			} else {
				arr.set(i-d, arry[i]);
			}
		}
		
		return arr;
	}
	
	public static void rotCheck() {
		List<Integer> myList = new ArrayList<Integer>();
		myList.add(1);
		myList.add(2);
		myList.add(3);
		myList.add(4);
		myList.add(5);
		leftRotation1(2, myList);
		String str = "[";
		for(Integer i : myList)
			str += String.valueOf(i) + ", ";
		str = str.substring(0, str.length()-2);
		str+="]";
		System.out.println(str);
		
		
	}
	
	public static String decryptPassword(String s) {
	    // Write your code here
		ArrayList<Character> charList = new ArrayList<Character>();
		for(int i = 0; i < s.length(); i++) {
			charList.add(s.charAt(i));
		}
		int ct = 0; 
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i)!='*' && s.charAt(i) != '0')
				ct++;
		}
	    char[] temp = new char[ct];
	    int currIndex = 0;
	    int numCount = -1;
	    for(int i = 0; i < charList.size(); i++) {
	    	while(currIndex < ct && (int)temp[currIndex] >= 49 && (int)temp[currIndex] <= 57)
	    		currIndex++;
	    	char c = charList.get(i);
	    	if((int)c >= 49 && (int)c <= 57) {
	    		numCount++;
	    		int tempIndex = temp.length - 1;
	    		int freq = 0;
	    		boolean found = false;
	    		for(int j = charList.size() - 1; j >= 0 && !found; j--) {
	    			if(charList.get(j) != '*') {
	    				if(charList.get(j) == '0') {
	    					if(freq == numCount) {
	    						temp[tempIndex] = c;
	    						found = true;
	    					}
	    					else {
	    						freq++;
	    						tempIndex--;
	    					}
	    				}
	    				else
	    					tempIndex--;
	    			}
	    		}
	    	}
	    	else if(i + 1 < charList.size() && (int)charList.get(i+1) >= 65 && (int)charList.get(i+1) <= 90 && (int)c >= 97 && (int)c <= 122) {
	    		temp[currIndex] = charList.get(i+1);
	    		temp[currIndex + 1] = c;
	    		i++;
	    		currIndex += 2;
	    	}
	    	else if(charList.get(i) == '*' || charList.get(i) == '0')
	    		continue;
	    	else {
	    		temp[currIndex] = c;
	    		currIndex++;
	    	}
	    	
	    }
	    String tempu = "";
	    for(int i = 0; i < temp.length; i++)
	    	tempu += String.valueOf(temp[i]);
	    return tempu;
	}
	
	public static String encryptPassword(String s) {
		List<Character> charList = new ArrayList<Character>();
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if((int)c >= 49 && (int) c <= 57) {
				charList.add('0');
				charList.add(0,c);
			} else if(i + 1 < s.length() && (int) c >= 97 && (int) c <= 122 && (int)s.charAt(i+1) >= 65 && (int)s.charAt(i+1) <= 90) {
				charList.add(s.charAt(i+1));
				charList.add(c);
				charList.add('*');
				i++;
			} else {
				charList.add(c);
			}
		}
		String temp = "";
		for(char c : charList)
			temp += String.valueOf(c);
		return temp;
	}
	
}

