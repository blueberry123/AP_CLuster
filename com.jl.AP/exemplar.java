package com.jl.AP;

import java.util.HashMap;

public class exemplar {

	public static HashMap<Integer,String> map=new HashMap<Integer,String>();
	public static void put(int k,String name){
		map.put(k,name);
	}
	public static void clear(){
		map.clear();
	}
}
