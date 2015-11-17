package com.jl.AP;

import java.util.ArrayList;
import java.util.List;
//得到一类同名作者的相似向量
public class allTestSampleMap {
	
	public static ArrayList<ArrayList<Double>> al=new ArrayList<ArrayList<Double>>();//存储作者向量
	public static ArrayList wendang=new ArrayList();//存储文档编号
	
    public static int size(){
    	int size=al.size();
    	return size;
    }
    
    public static List getVector(int i)
    {
    	List a=(List)al.get(i);
    	return a;
    }
    
   public  allTestSampleMap(ArrayList al,ArrayList wendang){
    	this.al=al;
    	this.wendang=wendang;
    }
    
    public static String get(int k){
    	 String name="";
    	 name=(String)wendang.get(k);
         return name;    	 
    }
}
