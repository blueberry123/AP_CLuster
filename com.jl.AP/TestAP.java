package com.jl.AP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestAP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		File fileDir = new File("wenben/xiangliang.txt");			 
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "utf-8"));	 
		String str; 
		
		
        List<Double> v1=new ArrayList<Double>();
        v1.add(0.1);
        v1.add(0.1);        
        List<Double> v2=new ArrayList<Double>();
        v2.add(0.3);
        v2.add(0.1);
        List<Double> v3=new ArrayList<Double>();
        v3.add(0.3);
        v3.add(0.3);
        List<Double> v4=new ArrayList<Double>();
        v4.add(0.1);
        v4.add(0.3);
        List<Double> v5=new ArrayList<Double>();
        v5.add(0.2);
        v5.add(0.2);
        List<Double> v6=new ArrayList<Double>();
        v6.add(0.5);
        v6.add(0.5);
        List<Double> v7=new ArrayList<Double>();
        v7.add(0.7);
        v7.add(0.5);
        List<Double> v8=new ArrayList<Double>();
        v8.add(0.7);
        v8.add(0.7);
        List<Double> v9=new ArrayList<Double>();
        v9.add(0.5);
        v9.add(0.7);
        List<Double> v10=new ArrayList<Double>();
        v10.add(0.6);
        v10.add(0.6);
        ArrayList al=new ArrayList();
        al.add(v1);
        al.add(v2);
        al.add(v3);
        al.add(v4);
        al.add(v5);
        al.add(v6);
        al.add(v7);
        al.add(v8);
        al.add(v9);
        al.add(v10);
        ArrayList al1=new ArrayList();
        al1.add("结点1");
        al1.add("结点2");
        al1.add("结点3");
        al1.add("结点4");
        al1.add("结点5");
        al1.add("结点6");
        al1.add("结点7");
        al1.add("结点8");
        al1.add("结点9");
        al1.add("结点10");
        allTestSampleMap ts=new allTestSampleMap(al,al1);
        apCluster ap=new apCluster();
        ap.apTest();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

}
