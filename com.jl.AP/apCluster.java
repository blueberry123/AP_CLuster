package com.jl.AP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class apCluster {
	
    //计算两个向量之间的欧式距离，并给出负值
    public static double sim(int x,int y){
    	double a=0.0;
        double b=0.0;
        double n1,n2;
        ArrayList v1=(ArrayList<Double>)allTestSampleMap.getVector(x);
        ArrayList v2=(ArrayList<Double>)allTestSampleMap.getVector(y);
        for(int i=0;i<v1.size();i++)
        {
            n1=(Double)v1.get(i);
            n2=(Double)v2.get(i);
            a=n1-n2;
            b+=a*a;
        }
        double ben=Math.sqrt(b);
        if(ben!=0.0){
        	System.out.println(-ben);
            return -ben;
        }else{
        	return 0.0;
        }
    }
	public HashMap  apTest(){
		
		double dampingFactor=0.7;//设置阻尼系数
		//设置迭代次数和收敛域，后者表示当聚类中心不再变化的次数达到coverageIteration算法结束  
	    int maxIteration = 0, coverageIteration = 0; 
	    
	    /*这里的allTestSampleMap是我的三百多篇文档形成的文本向量，此算法用来进行文本聚类，实验结果表明样本个数较少的情况下，参数合适能             
	    获得很好的效果*/  
	    int tsLength = allTestSampleMap.size();  
	    //A值和R值  
	    double[][] responsibility = new double[tsLength][tsLength];  
	    double[][] availability = new double[tsLength][tsLength];  
	    //A值初始化为0  
	    for (int i = 0; i < tsLength; i++) {  
	        for (int k = 0; k < tsLength; k++) {  
	                  responsibility[i][k] = 0;  
	                  availability[i][k] = 0;  
	             }  
	        }  
	    //上一次循环计算的A值和R值  
	    double[][] oldResponsibility = new double[tsLength][tsLength];  
	    double[][] oldAvailability = new double[tsLength][tsLength];  
	    //进行聚类，不断迭代直到到达预设的迭代次数或者聚类中心始终不再变化，newMarker和oldMarker分别存放新旧聚类中心  
	    ArrayList<Integer> newMarker = new ArrayList<Integer>();  
	    ArrayList<Integer> oldMarker = new ArrayList<Integer>();
       
	    double[][] sim=new double[tsLength][tsLength];
	    List<Double> al=new ArrayList<Double>();
	    for(int x=0;x<tsLength;x++){
	    	for(int y=0;y<tsLength;y++){
	    		if(x!=y){
	    			sim[x][y]=sim(x,y);
	    			al.add(sim(x,y));
	    		}
	    	}
	    }
	    int num=al.size();
        double[] array=new double[num];
        for(int i=0;i<num;i++){
        	array[i]=al.get(i);
        }
        Arrays.sort(array);
        double mid=0.0;
        if(num%2==1){
        	int t=(num+1)/2-1;
        	mid=array[t];
        }else{
        	int t=num/2;
        	mid=(array[t-1]+array[t])/2;
        	
        }
        for(int x=0;x<tsLength;x++){
	    	for(int y=0;y<tsLength;y++){
	    		if(x==y){
	    			sim[x][y]=mid;
	    		}
	    	}
	    }
	    
	    while (true) { 
	    	
	        for (int i = 0; i < tsLength; i++) {  
	            for (int k = 0; k < tsLength; k++) {  
	                oldResponsibility[i][k] = responsibility[i][k];  
	                double max1 = -Double.MAX_VALUE+1;    //修改过
	                for (int j = 0; j < tsLength; j++) {  
	                    if (j != k) {  
	                        if (availability[i][j] + sim[i][j] > max1) {  
	                            max1 = availability[i][j] + sim[i][j];  
	                        }  
	                    }  
	      
	                }  
	                responsibility[i][k] = sim[i][k] - max1;  
	                // System.out.println(i +"和"+k+": "+responsibility[i][k]);  
	                responsibility[i][k] = (1 - dampingFactor)  
	                        * responsibility[i][k] + dampingFactor  
	                        * oldResponsibility[i][k];  
	            }  
	      
	        }  
	        for (int i = 0; i < tsLength; i++) {  
	            for (int k = 0; k < tsLength; k++) {  
	                oldAvailability[i][k] = availability[i][k];  
	      
	                if (i == k) {  
	                    double max2 = 0;  
	                    for (int j = 0; j < tsLength; j++) {  
	                        if (j != k) {  
	                            if (responsibility[j][k] > 0) {  //修改过
	                                max2 += responsibility[j][k];  
	                            } else {  
	                                max2 += 0;  
	                            }  
	                        }  
	                    }  
	                    availability[i][k] = max2;  
	                } else {  
	                    double max3 = 0;  
	                    for (int j = 0; j < tsLength; j++) {  
	                        if (j != k && j != i) {  
	                            if (responsibility[j][k] > 0) {  
	                                max3 += responsibility[j][k];  
	                            } else {  
	                                max3 += 0;  
	                            }  
	                        }  
	      
	                    }  
	                    if (responsibility[k][k] + max3 > 0) {  
	                        availability[i][k] = 0;  
	                    } else {  
	                        availability[i][k] = responsibility[k][k] + max3;  
	                    }  
	                }  
	                 //System.out.println(i +"和"+k+": "+availability[i][k]);  
	                availability[i][k] = (1 - dampingFactor)  
	                        * availability[i][k] + dampingFactor  
	                        * oldAvailability[i][k];  
	            }  
	        }  
	              //exemplar表示聚类中心  
	        for (int k = 0; k < tsLength; k++) {  
	            if (responsibility[k][k] + availability[k][k] > 0.0) {  
	                exemplar.put(k,allTestSampleMap.get(k));  
	                newMarker.add(k);  
	            }  
	        }  
	      
	        Set<Integer> setA = new HashSet<Integer>(newMarker);  
	        Set<Integer> setB = new HashSet<Integer>(oldMarker);  
	        if (!setA.isEmpty() && !setB.isEmpty() && setA.equals(setB)) {  
	            coverageIteration++;  
	      
	        } else {  
	            coverageIteration = 0;  
	        }  
	        oldMarker.clear();  
	      
	        if (maxIteration > 1000 || coverageIteration > 50) {
	        	//System.out.println(newMarker);
	        	//System.out.println(oldMarker);
	        	//System.out.println(exemplar.map);
	            break;  
	        } else {  
	            maxIteration++;  
	            for (int j = 0; j < newMarker.size(); j++) {  
	                oldMarker.add(newMarker.get(j));  
	            }  
	            newMarker.clear();  
	            exemplar.clear();  
	        }  
	      
	    }
	    
	    System.out.println("已经求出了聚类点");
	    //根据聚类点，对各点所属聚类进行重新划分
	   // System.out.println(exemplar.map);
	    int clusters=exemplar.map.size();
	   
	    ArrayList a=new ArrayList();
	    Set set = exemplar.map.entrySet() ;
        java.util.Iterator it =exemplar.map.entrySet().iterator();
        while(it.hasNext()){
        java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
        int clu=(Integer) entry.getKey();// 返回与此项对应的键
        a.add(clu);
        }
        //System.out.println(a);
        HashMap cl=new HashMap();
	    for(int i=0;i<clusters;i++){
	    	ArrayList al1=new ArrayList();
	    	int jiedian=(Integer)a.get(i);
	    	cl.put(jiedian,al1);
	    }
        //System.out.println(cl);
        
        for(int i=0;i<tsLength;i++){
        	double max4=-Double.MAX_VALUE+1;
        	int flag=-1;
        	int cluster=-1;
        	for(int j=0;j<a.size();j++){
        		 int t=(Integer)a.get(j);
        		 if(i!=t){
        			 if(sim[i][t]>max4){
        				 max4=sim[i][t];
        				 flag=i;
        				 cluster=t;
        			 }
        		 }else{
        			 flag=-1;
        			 cluster=-1;
        			 ArrayList c=(ArrayList)cl.get(t);
             		 c.add(t);
        			 break;
        		 }
        		 
        	}
        	if(flag!=-1){
        		ArrayList c=(ArrayList)cl.get(cluster);
        		c.add(flag);
        	}
        	
        }
        
        System.out.println(cl);
        return cl;
	}

}
