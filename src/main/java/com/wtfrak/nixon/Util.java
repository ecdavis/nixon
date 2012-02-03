package com.wtfrak.nixon;

import java.util.Collection;
import java.util.Iterator;

public class Util {
	public static String join(Collection<String> strings, String delimiter) {
	    StringBuilder builder = new StringBuilder();
	    Iterator<String> iter = strings.iterator();
	    
	    while (iter.hasNext()) {
	        builder.append(iter.next());
	        
	        if (iter.hasNext()) {
	            builder.append(delimiter);
	        }
	    }
	    
	    return builder.toString();
	}
}
