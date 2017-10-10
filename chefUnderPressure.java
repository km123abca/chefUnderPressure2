import java.io.*;
import java.util.*;
import java.lang.*;

 public class chefUnderPressure
 {
 public static void main(String[] args) throws IOException
   {
 BufferedReader br=null;
 FileReader fr=null;
 try
     {
 fr=new FileReader("data\\inp.txt");
 br= new BufferedReader(fr);
 String n1;
 String[] l;
 int[][] adjmat;
 int[] prodmat;
 int n,k,b,a1,a2,q,livingCity,prodReq,cBuy;
 //for( int i=0;i<3;i++)
	 
 
		{   
		//parsing line1
		    n1= br.readLine();
	        l=n1.split("\\s");
			n=Integer.parseInt(l[0]);
			adjmat=new int[n][n];
			prodmat=new int[n];
			//initializing the adjacency matrix
			for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
			adjmat[i][j]=0;
			}
			}
			//initializing the product matrix
			for(int i=0;i<n;i++){
			prodmat[i]=0;
			}
			k=Integer.parseInt(l[1]);
			
		//parsing line2 
			n1= br.readLine();
			l=n1.split("\\s");
			b=Integer.parseInt(l[0]);
			
		//parsing subsequent n-1 lines
			for (int i=0;i<(n-1);i++){
			n1=br.readLine();
			l=n1.split("\\s");
			a1=Integer.parseInt(l[0]);
			a2=Integer.parseInt(l[1]);
			adjmat[a1-1][a2-1]=1;
			adjmat[a2-1][a1-1]=1;
			}
			
		//parsing the next n lines to assign products
		    for (int i=0;i<n;i++){
			n1=br.readLine();
			l=n1.split("\\s");
			prodmat[i]=Integer.parseInt(l[0]);
			}
			
		//parsing the next line to extract the number  of queries
		    n1=br.readLine();
			l=n1.split("\\s");
			q=Integer.parseInt(l[0]);
			//
			System.out.println("No of cities:"+n);
			System.out.println("No of products:"+k);
			System.out.println("city in which the king is staying:" +b);
			System.out.println("adjacency matrix is as follows");
			printmat(adjmat);
			System.out.println("product array is as follows");
			System.out.println(Arrays.toString(prodmat));
			
			/*
			int[] testDist=clos(-1,4,adjmat,0,1,0);  //1prev,2cur,3mat,4len,5dest,6dep
			System.out.println("shortest distance expected was 2 and what we got is:"+testDist[0]);
			 testing the closest distance function here
			*/
		
		//parsing the next q lines to process the q queries
		    for (int i=0;i<q;i++){
			n1=br.readLine();
			l=n1.split("\\s");
			livingCity=Integer.parseInt(l[0]);
			prodReq=Integer.parseInt(l[1]);
			//cBuy=processs(livingCity,prodReq,prodmat,adjmat,b,n,k);
			
			}
		   
		    
			
		
			
			
			 
		}
      }
 finally
     {
 ;
     }
   }
   public static void printmat(int[][] mat)
	  {int m,n; System.out.print('\n');
	   m=mat.length;
	   n=mat[0].length;
		  for(int i=0;i<m;i++)
          { for(int j=0;j<n;j++)
		   { System.out.print(mat[i][j]);
		     System.out.print(' ');	
		   }System.out.print('\n');
		
          }
		  
	  }
	  
	  //4-10-2017
	  //This is a function to find out the closest distance to the destination  from
	  //each point cur_city in the adjacency matrix adjmat
	public static int[] clos(int prev_city,int cur_city,int[][] adjmat,int l,int dest,int depth)
	  {
		int n,candidateLength,testLen;
		boolean rdflg;
		int[] retval,retmat;
		retval=new int[2];
		retmat=new int[2];
		n=adjmat.length;
		retval[0]=l;
		retval[1]=0;
		depth+=1;
		if (depth>100) 
			{
			System.out.println("crash at depth="+depth);
			return retval;
			}
		if (cur_city==dest)
		  {
		  retval[1]=1;
		  //retval=appendd(retval,cur_city);
		  return retval;
		  }
		testLen=9999; 
		rdflg=true;
		for (int j=0;j<n;j++)
		  {
		   if ( (j==prev_city)||(j==cur_city)) continue;
		   if (adjmat[cur_city][j]==1) 
		      {rdflg=false;
			  retmat=clos(cur_city,j,adjmat,l+1,dest,depth);
			  candidateLength=retmat[0];
			  if ((candidateLength<testLen)&&(retmat[1]==1)) testLen=candidateLength;
			  }
		  }
		 if(rdflg) 
			{
			retmat[0]=9999;
			retmat[1]=0;
			}
		 else
		    {
			retmat[0]=testLen;
			retmat[1]=1;
			}
		return retmat;	
		  
	  }
	  
	public static void processs(int livingCity,int prodReq,int[] prodmat,int[][] adjmat,int a,int n,int k){
	//core function starts here
	int lcity,prod;
	lcity=livingCity-1;
	prod=prodReq-1;
	
	
	
		
	;
	}
	
	public static int[] appendd(int[] a,int ele)
		{
		int n=a.length;
		int[] b=new int[n+1];
		System.arraycopy(a,0,b,0,n);
		b[n]=ele;
		return b;
		}
	
 }