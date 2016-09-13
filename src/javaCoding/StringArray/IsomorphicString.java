package javaCoding.StringArray;

import java.util.HashMap;

public class IsomorphicString {
	public static void main(String[] args) {
		String s = "eggg";
		String t = "ffff";
		System.out.println(isomorphic(s, t));
		isIsomorphic(s, t);
	}

	public static boolean isomorphic(String s, String t) {
		if (s == null || t == null)
			return false;
		if (s.length() != t.length())
			return false;
		if (s.length() == 0 && t.length() == 0)
			return true;
		
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				if (map.get(s.charAt(i)) != t.charAt(i)) {
					return false;
				}
			} else {
				map.put(s.charAt(i), t.charAt(i));
			}
		}
		return true;
	}
	public static void isIsomorphic(String s, String t)
	{
		boolean result =isomorphic(s, t);
		if(result == true)
		{
			System.out.println(s+" and "+t + " is isomorphic.");
		}else
		{
			System.out.println(s+" and "+t + " is not isomorphic.");
			
		}
		
	}
}
