import java.sql.*;
import java.text.DecimalFormat;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;


public class arm {
  
    
       public static void getnitems(double s, int Size, int taskn) throws IOException{
           File input= new File("system.in" );
           BufferedReader reader= null;
        
           reader= new BufferedReader(new InputStreamReader(new FileInputStream(input)));
           
           String[] get=null;
           String line= null; 
           String[] str= null;
          
           
           String[] temp=null;
           line = reader.readLine();
           str = line.split(" = ");
           get=str[1].split(",");
           String inp1=get[0];
           String inp2=str[2];
           reader.close();
           
          ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        DecimalFormat df = new DecimalFormat("#0.0000" );


         String url = "jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl" ;
         String username = inp1;
         String password = inp2;
     
         try {
        Class. forName("oracle.jdbc.driver.OracleDriver");
        conn =DriverManager. getConnection(url,username,password);                    
        stmt = conn.createStatement();
        FileOutputStream fos = new FileOutputStream("system.out."+taskn,true);
     

      
         int i=1;
         double countnum=s*1731;
      
        int size1=Size;
        int i1=i;
        String select= "";
        while(i1<=size1-1){
             select+= "i"+i1+".ItemName," ;
             i1++;}
       select=select+ "i"+i1+".ItemName " +",count(*)" ;
  //      System.out.println(select);          
     
        int size2=Size;
        int i2=i;
        String from= "";
        while(i2<=size2-1){
            from+= "Trans t"+i2+",Items i" +i2+",";
             i2++;}
        from=from+ "Trans t"+i2+", Items i" +i2;
   //     System.out.println(from);
     
        int size31=Size;
        int i31=i;
        String where1= "";
        while(i31<=size31){
            where1+= "t"+i31+".ItemID=i" +i31+".ItemID AND ";
             i31++;}        
     //   System.out.println(where1);
     
        int size32=Size;
        int i32=i;
        String where2= "";
        while(i32<=size32-1){
            where2+= "t"+i32+".TransID=t" +(i32+1)+".TransID AND ";
             i32++;}
     
        int size33=Size;
        int i33=i;
        String where3= "";
        while(i33<=size33-2){
            where3+= "t"+i33+".ItemID<t" +(i33+1)+".ItemID AND ";
             i33++;}
        where3=where3+ "t"+i33+".ItemID<t" +(i33+1)+".ItemID";
     
        String where= "";
        where=where1+where2+where3;
   //     System.out.println(where);
     
        int size4=Size;
        int i4=i;
        String group_by= "";
        while(i4<=size4-1){
             group_by+= "i"+i4+".ItemName," ;
             i4++;}
        group_by=group_by+ "i"+i4+".ItemName " ;
   //    System.out.println(group_by);   
     
        rs = stmt.executeQuery( " SELECT "+select+" FROM " +from+" WHERE "+where+" GROUP BY "+group_by+ " HAVING COUNT(*) >="+countnum );

        System.setOut(new PrintStream(fos));
        while(rs.next())
        {   System. out.print("{" );
             for(int i5=1;i5<=Size-1;i5+=1){
             System. out.print( rs.getString(i5)+"," );
        }
             System. out.print( rs.getString(Size));
             System. out.print("}" );
             double ss = rs.getDouble("count(*)" )/1731*100;
             System. out.println(", s=" + df.format(ss) + "%");
        }
     
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        } catch (ClassNotFoundException e)
                              {e.printStackTrace();}
           catch (SQLException e)
                              {e.printStackTrace();}
          finally {
          try {
        rs.close();
        stmt.close();
        conn.close();
          } catch (Exception e)
          {e.printStackTrace();}
          }
  }

     public static void createnitems(double s, int Size) throws IOException{
         File input= new File("system.in" );
         BufferedReader reader= null;
      
         reader= new BufferedReader(new InputStreamReader(new FileInputStream(input)));
         
         String[] get=null;
         String line= null; 
         String[] str= null;
        
         
         String[] temp=null;
         line = reader.readLine();
         str = line.split(" = ");
         get=str[1].split(",");
         String inp1=get[0];
         String inp2=str[2];
         reader.close();
         
         ResultSet rs = null;
       Statement stmt = null;
       Connection conn = null;
       DecimalFormat df = new DecimalFormat( "#0.0000" );


        String url = "jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl" ;
        String username = inp1;
        String password = inp2;
   
        try {
       Class. forName("oracle.jdbc.driver.OracleDriver");
       conn =DriverManager. getConnection(url,username,password);                   
       stmt = conn.createStatement();


        int i=1;
        double countnum=s*1731;
    
       int size1=Size;
       int i1=i;
       String select= "";
       while(i1<=size1-1){
            select+= "i"+i1+".Itemid," ;
            i1++;}
      select=select+ "i"+i1+".Itemid " ;
//       System.out.println(select);         
   
       int size2=Size;
       int i2=i;
       String from= "";
       while(i2<=size2-1){
           from+= "Trans t"+i2+",Items i" +i2+",";
            i2++;}
       from=from+ "Trans t"+i2+", Items i" +i2;
//      System.out.println(from);
   
       int size31=Size;
       int i31=i;
       String where1= "";
       while(i31<=size31){
           where1+= "t"+i31+".ItemID=i" +i31+".ItemID AND ";
            i31++;}       
//      System.out.println(where1);
   
       int size32=Size;
       int i32=i;
       String where2= "";
       while(i32<=size32-1){
           where2+= "t"+i32+".TransID=t" +(i32+1)+".TransID AND ";
            i32++;}
  //     System.out.println(where2);
   
       int size33=Size;
       int i33=i;
       String where3= "";
       while(i33<=size33-2){
           where3+= "t"+i33+".ItemID<t" +(i33+1)+".ItemID AND ";
            i33++;}
       where3=where3+ "t"+i33+".ItemID<t" +(i33+1)+".ItemID";
//      System.out.println(where3);
   
       String where= "";
       where=where1+where2+where3;
  //     System.out.println(where);
   
       int size4=Size;
       int i4=i;
       String group_by= "";
       while(i4<=size4-1){
            group_by+= "i"+i4+".Itemid," ;
            i4++;}
       group_by=group_by+ "i"+i4+".Itemid " ;
  //    System.out.println(group_by);  
   
       int sizex=Size;
       int ix=1;
       String createtemp= "";
       while(ix<=sizex-1){
            createtemp+= " id"+ix+" int," ;
            ix++;}
       createtemp=createtemp+ " id"+ix+" int" ;
    //   System.out.println(createtemp);
   
   
   
       rs = stmt.executeQuery( "CREATE TABLE tempsizen("+createtemp+")" );
//       System.out.println("INSERT INTO tempsizen SELECT "+select+" FROM "+from+" WHERE "+where+" GROUP BY "+group_by+" HAVING COUNT(*) >="+countnum);
       rs = stmt.executeQuery( "INSERT INTO tempsizen SELECT " +select+" FROM "+from+" WHERE "+where+ " GROUP BY "+group_by+" HAVING COUNT(*) >="+countnum );
    
////////////
       int sizey=Size;
       int iy=i;
       String createtempk= "";
       while(iy<=sizey-1){
            createtempk+= "id"+iy+" integer," ;
           iy++;}
       createtempk= "id integer,"+createtempk+"id" +iy+" integer";
  //   System.out.println(createtempk);
 
rs = stmt.executeQuery( "CREATE TABLE tempsizenk("+createtempk+ ")");
//System.out.println("test1");
rs = stmt.executeQuery( "CREATE SEQUENCE seq_tempsizenk start with 1");
//System.out.println("test2");
rs = stmt.executeQuery("CREATE OR REPLACE TRIGGER tempsizenk BEFORE INSERT ON tempsizenk FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN SELECT SEQ_tempsizenk.NEXTVAL INTO :NEW.id FROM DUAL; END;");
//System.out.println("test3");

/////////
int sizem=Size;
int im=i;
String insertk1="";
while(im<=sizem-1){
insertk1+="id"+im+ "," ;
im++;}
insertk1=insertk1+"id"+(im);
//System.out.println(insertk1);

int sizen=Size;
int in=i;
String insertk2="";
while(in<=sizen-1){
insertk2+="tn.id"+in+ "," ;
in++;}
insertk2=insertk2+"tn.id"+(in);
//System.out.println(insertk2);
/////////
//System.out.println("INSERT INTO tempsizen1k("+insertk1+") SELECT "+insertk2+" FROM tempsizen tn");
rs = stmt.executeQuery("INSERT INTO tempsizenk("+insertk1+ ") SELECT "+insertk2+" FROM tempsizen tn");


rs = stmt.executeQuery("CREATE TABLE tpsizen(isetid integer, itemid integer)" );

for(int ii=i;ii<=Size;ii+=1){
    rs = stmt.executeQuery( "INSERT INTO tpsizen SELECT tk.id,tk.id" +ii+" From  tempsizenk tk" );
}
rs = stmt.executeQuery("CREATE TABLE sizen(isetid integer, itemid integer)" );
rs = stmt.executeQuery("INSERT INTO sizen SELECT tpsizen.isetid,tpsizen.itemid FROM tpsizen ORDER BY isetid ASC");


  //     while(rs.next())
//      {   //////////////////////////////////
//      }
   

       } catch (ClassNotFoundException e)
                             {e.printStackTrace();}
          catch (SQLException e)
                             {e.printStackTrace();}
         finally {
         try {
       rs.close();
       stmt.close();
       conn.close();
         } catch (Exception e)
         {e.printStackTrace();}
         }
}

 
     public static void get1item(double s,int taskn) throws IOException{  
         File input= new File("system.in" );
         BufferedReader reader= null;
      
         reader= new BufferedReader(new InputStreamReader(new FileInputStream(input)));
         
         String[] get=null;
         String line= null; 
         String[] str= null;
        
         
         String[] temp=null;
         line = reader.readLine();
         str = line.split(" = ");
         get=str[1].split(",");
         String inp1=get[0];
         String inp2=str[2];
         reader.close();
         
         
          ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        DecimalFormat df = new DecimalFormat("#0.0000" );


         String url = "jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl" ;
         String username = inp1;
         String password = inp2;
         FileOutputStream fos = new FileOutputStream("system.out."+taskn,true);
     
         try {
        Class. forName("oracle.jdbc.driver.OracleDriver");
        conn =DriverManager. getConnection(url,username,password);
     
     
     
                       
        stmt = conn.createStatement();
        rs = stmt.executeQuery( "SELECT i.ItemName, count(*) FROM Items i, Trans t WHERE i.ItemID=t.ItemID GROUP BY i.ItemName HAVING COUNT(*)>="+s+"*1731"  );
     
        System.setOut(new PrintStream(fos));
         while(rs.next())
        {
              System. out.print("{" +rs.getString(1)+"}, ");
              double ss = rs.getDouble("count(*)" )/1731*100;
           
             System. out.println("s=" + df.format(ss) + "%");
         }
     


      
      
      
      
      
      
 
     
 		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
     
        } catch (ClassNotFoundException e)
                              {e.printStackTrace();}
           catch (SQLException e)
                              {e.printStackTrace();}
          finally {
          try {
        rs.close();
        stmt.close();
        conn.close();
          } catch (Exception e)
          {e.printStackTrace();}
          }
      
     }
  
  
     public static void task1(double s) throws IOException{
    	 
          //System. out.println("*************TASK 1**************" );
          FileOutputStream fos = new FileOutputStream("system.out.1");
          arm. get1item(s,1);
          System.setOut(new PrintStream(fos));
  		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
     }
     
     public static void task2(double s) throws IOException{
          //System. out.println("*************TASK 2**************" );
          FileOutputStream fos = new FileOutputStream("system.out.2");
          arm. get1item(s,2);
          arm. getnitems(s, 2,2);
          System.setOut(new PrintStream(fos));
  		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
     }
     public static void task3(double s, int Size) throws IOException{
         // System. out.println("*************TASK 3**************" );
          FileOutputStream fos = new FileOutputStream("system.out.3");
          arm. get1item(s,3);
          System.setOut(new PrintStream(fos));
          for(int i6=2;i6<=Size;i6+=1){
            arm. getnitems(s, i6,3);      }
  		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
     }

     public static void sub_task4(double s, int Size, double confidence, int line,int taskn) throws IOException{
         File input= new File("system.in" );
         BufferedReader reader= null;
      
         reader= new BufferedReader(new InputStreamReader(new FileInputStream(input)));
         
         String[] get=null;
         String line2= null; 
         String[] str= null;
        
         
         String[] temp=null;
         line2 = reader.readLine();
         str = line2.split(" = ");
         get=str[1].split(",");
         String inp1=get[0];
         String inp2=str[2];
         reader.close();
         
         ResultSet rs = null;
         Statement stmt = null;
         Connection conn = null;
         DecimalFormat df = new DecimalFormat("#0.0000" );


        String url = "jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl" ;
        String username = inp1;
        String password = inp2;
    
        try {
       Class. forName("oracle.jdbc.driver.OracleDriver");
       conn =DriverManager. getConnection(url,username,password);                    
       stmt = conn.createStatement();
       FileOutputStream fos = new FileOutputStream("system.out."+taskn,true);
    
    //   arm.todrop(Size,line);
    
       int i=1;
       double countnum=s*1731;
   
      int size1=Size;
      int i1q=i;
      String select= "";
      while(i1q<=size1-1){
           select+= "i"+i1q+".Itemid," ;
           i1q++;}
     select=select+ "i"+i1q+".Itemid " ;
//      System.out.println(select);         
  
      int size2=Size;
      int i2q=i;
      String from= "";
      while(i2q<=size2-1){
          from+= "Trans t"+i2q+",Items i" +i2q+",";
           i2q++;}
      from=from+ "Trans t"+i2q+", Items i" +i2q;
//      System.out.println(from);
  
      int size31=Size;
      int i31=i;
      String where1= "";
      while(i31<=size31){
          where1+= "t"+i31+".ItemID=i" +i31+".ItemID AND ";
           i31++;}       
//      System.out.println(where1);
  
      int size32=Size;
      int i32=i;
      String where2= "";
      while(i32<=size32-1){
          where2+= "t"+i32+".TransID=t" +(i32+1)+".TransID AND ";
           i32++;}
//     System.out.println(where2);
  
      int size33=Size;
      int i33=i;
      String where3= "";
      while(i33<=size33-2){
          where3+= "t"+i33+".ItemID<t" +(i33+1)+".ItemID AND ";
           i33++;}
      where3=where3+ "t"+i33+".ItemID<t" +(i33+1)+".ItemID";
//      System.out.println(where3);
  
      String where= "";
      where=where1+where2+where3;
//     System.out.println(where);
  
      int size4=Size;
      int i41=i;
      String group_by= "";
      while(i41<=size4-1){
           group_by+= "i"+i41+".Itemid," ;
           i41++;}
      group_by=group_by+ "i"+i41+".Itemid " ;
//    System.out.println(group_by);  
  
      int sizex=Size;
      int ix=1;
      String createtemp= "";
      while(ix<=sizex-1){
           createtemp+= " id"+ix+" int," ;
           ix++;}
      createtemp=createtemp+ " id"+ix+" int" ;
   //   System.out.println(createtemp);
  
   //   System.out.println("ldj1");
  
      rs = stmt.executeQuery( "CREATE TABLE tempsizen("+createtemp+")" );
//      System.out.println("INSERT INTO tempsizen SELECT "+select+" FROM "+from+" WHERE "+where+" GROUP BY "+group_by+" HAVING COUNT(*) >="+countnum);
      rs = stmt.executeQuery( "INSERT INTO tempsizen SELECT " +select+" FROM "+from+" WHERE "+where+ " GROUP BY "+group_by+" HAVING COUNT(*) >="+countnum );
   
   
    
    
    
    
       String createmain= "";
       String insertmain= "";
       String selectmain= "";
       String frommain= "";
       String wheremain1= "";
       String wheremain2= "";
       String groupmain= "";
       int iv=1;
       while(iv<=Size){
            insertmain+= "id"+iv+"," ;
            wheremain1+= " tn.id"+iv+"=t" +iv+".itemid and ";
            frommain+= " trans t"+iv+"," ;
            selectmain+= "tn.id"+iv+"," ;
            createmain+= "id"+iv+" integer," ;
            iv++;
       }
       int ivv=1;
       while(ivv<=Size-1){
            groupmain+= "tn.id"+ivv+"," ;
            wheremain2+= " t"+ivv+".transid=t" +(ivv+1)+".transid and t"+ivv+".itemid<t"+(ivv+1)+ ".itemid and";
            ivv++;
       }
       groupmain=groupmain+ "tn.id"+ivv;
       wheremain2=wheremain2+ " 1=1 ";
       createmain=createmain+ "co integer";

       insertmain=insertmain+ "co";
       selectmain=selectmain+ "count(*)";
       frommain=frommain+ "tempsizen tn";
    
   //    System.out.println("in order to get main");
      // System.out.println("ldj2");
       rs = stmt.executeQuery( "CREATE TABLE main(id integer,"+createmain+")");
 //      System.out.println("ldj3");
       rs = stmt.executeQuery( "CREATE SEQUENCE seq_main start with 1" );
       rs = stmt.executeQuery( "CREATE OR REPLACE TRIGGER main BEFORE INSERT ON main FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN SELECT SEQ_main.NEXTVAL INTO :NEW.id FROM DUAL; END;");
    //   System.out.println("INSERT INTO main("+insertmain+") SELECT "+selectmain+" FROM "+frommain+" WHERE "+wheremain1+" "+wheremain2+" GROUP BY "+groupmain);
    
       rs = stmt.executeQuery( "INSERT INTO main("+insertmain+") SELECT "+selectmain+" FROM "+frommain+ " WHERE "+wheremain1+" " +wheremain2+" GROUP BY "+groupmain);     
    
       ////////////////////////
    //   System.out.println("yes?");
    //   System.out.println("ldj4");
       rs = stmt.executeQuery( "CREATE TABLE tpsizen(isetid integer, itemid integer)" );
    //   System.out.println("yes?");
       for(int ii=i;ii<=Size;ii+=1){
           rs = stmt.executeQuery( "INSERT INTO tpsizen SELECT tk.id,tk.id"+ii+" From  main tk");
       }
   //    System.out.println("ldj5");
       rs = stmt.executeQuery( "CREATE TABLE sizen(isetid integer, itemid integer)" );
       rs = stmt.executeQuery( "INSERT INTO sizen SELECT tpsizen.isetid,tpsizen.itemid FROM tpsizen ORDER BY isetid ASC");
    
   //    System.out.println("in order to get main");

     //  System.out.println("test w");

       String insertnote= "";
       for(int io1=1;io1<=Size-1;io1+=1){
       for(int i1=io1;i1<=Size-1;i1+=1){
       insertnote+= " s"+io1+".itemid!=s" +(i1+1)+".itemid and ";
       }}
   //    System.out.println(insertnote );
       // s1.itemid !=s2.itemid and  s1. itemid!=s3.itemid and  s1.itemid!=s4.itemid and  s2.itemid!=s3. itemid and  s2.itemid!=s4.itemid and  s3.itemid!=s4. itemid and
    
       String isetid= "";
       int i2=i;
       while(i2<=Size-1){
            isetid+= "s"+i2+".isetid=" +line+" and ";
       i2++;}
       isetid=isetid+ "s"+i2+".isetid=" +line;
   //    System.out.println(isetid);
       // s1.isetid =1 and s2.isetid=1 and s3. isetid=1 and s4.isetid =1
    
       String fromsizen= "";
       int i3=i;
       while(i3<=Size-1){
            fromsizen+= " sizen s"+i3+", " ;
       i3++;}
       fromsizen=fromsizen+ " sizen s"+i3;  
       String selectseizen= "";
       int i4=i;
       while(i4<=Size-1){
            selectseizen+= " s"+i4+".itemid, " ;
       i4++;}
       selectseizen=selectseizen+ "s"+i4+".itemid " ;
    
       String createsubt1= "";
       int i5=i;
       while(i5<=Size-1){
            createsubt1+= " id"+i5+" integer," ;
       i5++;}
       createsubt1=createsubt1+ " id"+i5+" integer" ;
     //  System.out.println("ldj6");
       rs = stmt.executeQuery( "CREATE TABLE subt"+line+"(" +createsubt1+ ")" );
       String insertsubt= "";
       insertsubt=  "INSERT INTO subt"+line+" SELECT " +selectseizen+" FROM "+fromsizen+" WHERE "+insertnote+isetid;
    
       rs = stmt.executeQuery(insertsubt);
   //    System.out.println("ldj7");
       rs = stmt.executeQuery( "CREATE TABLE sub"+line+"1(id1 integer,co integer)");
       rs = stmt.executeQuery( "INSERT INTO sub"+line+"1 SELECT st11.id1,count(*) from subt"+line+ " st11, trans t  where st11.id1=t.itemid  group by st11.id1");
    
       // 建立完成 subt1
 
       for(int ii=1;ii<=Size-1;ii+=1){
           
             String createname= "";
             String createtf= "";
             createname= "tempfinal"+line+ii;
              int in=1;
              while(in<=Size){
              createtf+= " id"+in+" integer," ;
              in++;
              }
              createtf=createtf+ "co integer, c float";
              rs = stmt.executeQuery( "CREATE TABLE "+createname+"("+createtf+ ")" );
      }
 
      for ( int isub=2;isub<Size;isub+=1){
           String subtcreate= "";
           String subtwhere= "";
           String subtfrom= "";
           String subtselect= "";
          String subcreate= "";
          String subtname= "";
          String subselect= "";
          String subfrom= "";
          String subwhere1= "";
          String subwhere2= "";
          String subwhere3= "";
          String subwhere= "";
          String subgroup= "";
          String subname= "";
      
       subtname= "subt"+line+isub;
       subname= "sub"+line+isub;
       int insub=1;
       while(insub<=isub-1){
            subtcreate+= "id"+insub+" integer," ;
            subtselect+= "st1.id"+insub+"," ;
            subtfrom= "subt"+line+" st1" ;
            subtwhere+= "st1"+".id" +insub+"<st1.id"+(insub+1)+ " and ";
         
            subgroup+= " st1"+isub+".id" +insub+",";
            subwhere1+= " st1"+isub+".id" +insub+"=t"+insub+ ".itemid and ";
            subwhere2+= " t"+insub+".transid=t" +(insub+1)+".transid and";
           subwhere3+= " t"+insub+".itemid<t" +(insub+1)+".itemid and";
           subfrom+= " ,trans t"+insub;
            subselect+= " st1"+isub+".id" +insub+",";
            subcreate+= "id"+insub+" integer," ;
            insub++;
               }
       subtcreate=subcreate+ "id"+insub+" integer" ;
       subtwhere=subtwhere+ " 1=1 ";
       subtselect=subtselect+ "st1.id"+insub;
       subgroup=subgroup+ " st1"+isub+".id" +insub;
       subwhere1=subwhere1+ "st1"+isub+".id" +insub+"=t"+insub+ ".itemid ";
       subwhere=subwhere2+subwhere3+subwhere1;
       subfrom= "subt"+line+"" +isub+" st1"+isub+subfrom+ " ,trans t"+insub;
       subselect=subselect+ " st1"+isub+".id" +insub+", count(*)";
       subcreate=subcreate+ "id"+insub+" integer, co integer" ;
    
   //    System.out.println("ldj8");
       rs = stmt.executeQuery( "CREATE TABLE "+subtname+"(" +subtcreate+ ")");
    //   System.out.println("ldj9");
       rs = stmt.executeQuery( "CREATE TABLE "+subname+"(" +subcreate+ ")");
       rs = stmt.executeQuery( "INSERT INTO "+subtname+" SELECT DISTINCT "+subtselect+" FROM "+subtfrom+ " WHERE "+subtwhere);
       rs = stmt.executeQuery( "INSERT INTO "+subname+" SELECT "+subselect+" FROM "+subfrom+ " WHERE "+subwhere+" GROUP BY " +subgroup);
   

        
      }
   
   
      for( int out=1;out<=Size-1;out+=1){
           String tfwhere= "";
           String tfwhere2= "";
           String tfselect= "";
           String tffrom= "";
        
           String tfselect1= "";
           String tffrom2= "";
           String tffrom1= "";
           tffrom2+= "sub"+line+out+"," ;
           int ia=i;
           while(ia<=Size-out){
                tfwhere2+= "s"+ia+".isetid=" +line+" and " ;
              tfselect1+= "s"+ia+".itemid," ;
              tffrom1+= "sizen s"+ia+"," ;
           ia++;
           }
           String tfwhere1= "";
             int ic=i;
           while(ic<=Size-out-1){
                tfwhere1+= " s"+ic+".itemid<s" +(ic+1)+".itemid and ";
           ic++;
           }
           int ib=i;
           String tfselect2= "";
           while(ib<=out-1){
             
                tfselect2+= "sub"+line+out+".id" +ib+",";
             
                ib++;
           }
           tfwhere=tfwhere1+tfwhere2+ "m.id="+line;
           tfselect2=tfselect2+ "sub"+line+out+".id" +ib+",";
        
         //  System.out.println(tfselect2);
           tfselect=tfselect2+tfselect1+ "m.co, m.co/sub"+line+out+".co" ;
           tffrom=tffrom2+tffrom1+ " main m";

           rs = stmt.executeQuery( "INSERT INTO tempfinal"+line+out+" SELECT "+tfselect+" FROM "+tffrom+ " WHERE "+tfwhere);
      }

      for( int ii=1;ii<=Size-1;ii+=1){
          
             String createname= "";
             String createtf= "";
             createname= "final"+line+ii;
              int in=1;
              while(in<=Size){
              createtf+= " id"+in+" VARCHAR(50)," ;
              in++;
              }
              createtf=createtf+ "co integer, c float";
           //   System.out.println(createname);
           //   System.out.println(createtf);
    //          System.out.println("ldj10");
              rs = stmt.executeQuery( "CREATE TABLE "+createname+"("+createtf+ ")" );
      }
   

      String finanote= "";
      for( int io1=i;io1<=Size-1;io1+=1){
      for( int i1=io1;i1<=Size-1;i1+=1){
           finanote+= " tf.id"+io1+"!=tf.id" +(i1+1)+" and ";
      }}
      finanote=finanote+ " 1=1 ";
  //    System.out.println(finanote);
   
      int ic=1;
      String selectchange= "";
      String fromchange= "";
      String wherechange= "";
      while(ic<=Size){
           selectchange+= "i"+ic+".itemname," ;
           fromchange+= "items i"+ic+"," ;
           wherechange+= "tf.id"+ic+"=i" +ic+".itemid and ";
           ic++;
      }
  //    System.out.println(selectchange );
  //    System.out.println(fromchange );
   //   System.out.println(wherechange);

   
   //   System.out.println("succeed????????????????????");
   
      for(i=1;i<=Size-1;i+=1){
        
         //  System.out.println("INSERT INTO final"+line+""+i+" SELECT "+selectchange+" tf.co,tf.c from "+ fromchange+"tempfinal "+line+i+" tf where "+finanote+" and "+wherechange+" tf.c>="+confidence);
        

           rs = stmt.executeQuery( "INSERT INTO final"+line+"" +i+" SELECT "+selectchange+" tf.co,tf.c from "+fromchange+ "tempfinal"+line+i+" tf where " +finanote+" and "+wherechange+ " tf.c>="+confidence);
        

           rs = stmt.executeQuery( "SELECT * FROM FINAL"+line+i);
        
           System.setOut(new PrintStream(fos));
          while(rs.next())
          {   System. out.print("{{" );
               for(int im=1;im<=i-1;im+=1){
                System. out.print(rs.getString(im)+", " );
               }
               System. out.print(rs.getString(i));
               System. out.print("} -> {" );
               for(int in=i+1;in<=Size-1;in+=1){
                    System. out.print( rs.getString(in)+", " );
               }
               System. out.print( rs.getString(Size));
               System. out.print("}} " );
            
              double ss = rs.getDouble(Size+1)/1731*100;
              System. out.print(", s=" + df.format(ss) + "%, c=");
           
              double cc = rs.getDouble(Size+2)*100;
               System. out.println(df.format(cc)+"%" );
          }
          }
   
      arm.todrop(Size,line);
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
       } catch (ClassNotFoundException e)
                             {e.printStackTrace();}
          catch (SQLException e)
                             {e.printStackTrace();}
         finally {
         try {
       rs.close();
       stmt.close();
       conn.close();
         } catch (Exception e)
         {e.printStackTrace();}
         }
    }
  
     public static void sub2_task4(double s, int Size, double confidence) throws IOException{
         File input= new File("system.in" );
         BufferedReader reader= null;
      
         reader= new BufferedReader(new InputStreamReader(new FileInputStream(input)));
         
         String[] get=null;
         String line= null; 
         String[] str= null;
        
         
         String[] temp=null;
         line = reader.readLine();
         str = line.split(" = ");
         get=str[1].split(",");
         String inp1=get[0];
         String inp2=str[2];
         reader.close();
         
         ResultSet rs = null;
         Statement stmt = null;
         Connection conn = null;
         DecimalFormat df = new DecimalFormat( "#0.0000" );


          String url = "jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl" ;
          String username = inp1;
          String password = inp2;
      
          try {
         Class. forName("oracle.jdbc.driver.OracleDriver");
         conn =DriverManager. getConnection(url,username,password);
         stmt = conn.createStatement();
      
      
     
         int i=1;
         double countnum=s*1731;
     
        int size1=Size;
        int i1=i;
        String select= "";
        while(i1<=size1-1){
             select+= "i"+i1+".Itemid," ;
             i1++;}
       select=select+ "i"+i1+".Itemid " ;         
    
        int size2=Size;
        int i2=i;
        String from= "";
        while(i2<=size2-1){
            from+= "Trans t"+i2+",Items i" +i2+",";
             i2++;}
        from=from+ "Trans t"+i2+", Items i" +i2;
    
        int size31=Size;
        int i31=i;
        String where1= "";
        while(i31<=size31){
            where1+= "t"+i31+".ItemID=i" +i31+".ItemID AND ";
             i31++;}       
    
        int size32=Size;
        int i32=i;
        String where2= "";
        while(i32<=size32-1){
            where2+= "t"+i32+".TransID=t" +(i32+1)+".TransID AND ";
             i32++;}
    
        int size33=Size;
        int i33=i;
        String where3= "";
        while(i33<=size33-2){
            where3+= "t"+i33+".ItemID<t" +(i33+1)+".ItemID AND ";
             i33++;}
        where3=where3+ "t"+i33+".ItemID<t" +(i33+1)+".ItemID";
    
        String where= "";
        where=where1+where2+where3;
    
        int size4=Size;
        int i4=i;
        String group_by= "";
        while(i4<=size4-1){
             group_by+= "i"+i4+".Itemid," ;
             i4++;}
        group_by=group_by+ "i"+i4+".Itemid " ; 
    
        int sizex=Size;
        int ix=1;
        String createtemp= "";
        while(ix<=sizex-1){
             createtemp+= " id"+ix+" int," ;
             ix++;}
        createtemp=createtemp+ " id"+ix+" int" ;
         //////attention!!!!!!!!!!!
        rs = stmt.executeQuery( "CREATE TABLE usecount("+createtemp+")" );
     //   System.out.println("INSERT INTO usecount SELECT "+select+" FROM "+from+" WHERE "+where+" GROUP BY "+group_by+" HAVING COUNT(*) >="+countnum);
        rs = stmt.executeQuery( "INSERT INTO usecount SELECT " +select+" FROM "+from+" WHERE "+where+ " GROUP BY "+group_by+" HAVING COUNT(*) >="+countnum );
     
    //    System. out.println("test" );
      
      
         int count = 0;
         ResultSet rs1 = null;
         stmt = conn.createStatement();
         rs1 = stmt.executeQuery( "select count(*)  from usecount"     );
         rs1.next();
         count = rs1.getInt(1);
  //       System. out.println(count);
      
     
         for(int ii=1;ii<=count;ii+=1){
          
             arm. sub_task4(s, Size, confidence, ii,4);
             // arm.todrop(sizex, ii);
         }
      
      
         /////test for get count(*) up
       
        
  
         rs = stmt.executeQuery( "DROP TABLE usecount");

      
         } catch (ClassNotFoundException e)
                               {e.printStackTrace();}
            catch (SQLException e)
                               {e.printStackTrace();}
           finally {
           try {
         rs.close();
         stmt.close();
         conn.close();
           } catch (Exception e)
           {e.printStackTrace();}
           }
      
     }
  
     public static void todrop(int Size, int line) throws IOException{
         File input= new File("system.in" );
         BufferedReader reader= null;
      
         reader= new BufferedReader(new InputStreamReader(new FileInputStream(input)));
         
         String[] get=null;
         String line2= null; 
         String[] str= null;
        
         
         String[] temp=null;
         line2 = reader.readLine();
         str = line2.split(" = ");
         get=str[1].split(",");
         String inp1=get[0];
         String inp2=str[2];
         reader.close();
         
          ResultSet rs = null;
          Statement stmt = null;
          Connection conn = null;
          DecimalFormat df = new DecimalFormat( "#0.0000" );


         String url = "jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl" ;
         String username = inp1;
         String password = inp2;
     
         try {
        Class. forName("oracle.jdbc.driver.OracleDriver");
        conn =DriverManager. getConnection(url,username,password);                    
        stmt = conn.createStatement();
     
     
        rs = stmt.executeQuery( "DROP TABLE tempsizen");
     //   System.out.println("can1");
        rs = stmt.executeQuery( "DROP TABLE main");
     //   System.out.println("can2");
        rs = stmt.executeQuery( "DROP SEQUENCE seq_main");
     //   System.out.println("can3");
        rs = stmt.executeQuery( "DROP TABLE tpsizen");
    //    System.out.println("can4");
     
     
     //   System.out.println("canceled main");
    //    System.out.println("can5");
   
        rs = stmt.executeQuery( "DROP TABLE sizen");
      //  System.out.println("can6");

        rs = stmt.executeQuery( "DROP TABLE subt"+line);
   //     System.out.println("can7");
        rs = stmt.executeQuery( "DROP TABLE sub"+line+"1" );
   //     System.out.println("can8");
  
        //////////////////////未加 size循环！！！！！！！！！！！！
     

        for(int ii=1;ii<=Size-1;ii+=1){
               String createname= "";
               createname= "tempfinal"+line+ii;
      //         System.out.println("DROP TABLE "+ createname);
               rs = stmt.executeQuery( "DROP TABLE "+createname);
        }
     
    //    System.out.println("can9");
    
        for (int isub=2;isub<=Size-1;isub+=1){
             String subname= "";
             String subtname= "";
             subtname= "subt"+line+isub;
            subname= "sub"+line+isub;
             rs = stmt.executeQuery( "DROP TABLE "+subtname);
             rs = stmt.executeQuery( "DROP TABLE "+subname);
     
        }
   //     System.out.println("can10");
     
     
        for(int ii=1;ii<=Size-1;ii+=1){
             String createname= "";
             createname= "final"+line+ii;
    //         System.out.println("DROP TABLE "+ createname);
             rs = stmt.executeQuery( "DROP TABLE "+createname);
      }
     
   //     System.out.println("can11");
     


        } catch (ClassNotFoundException e)
                              {e.printStackTrace();}
           catch (SQLException e)
                              {e.printStackTrace();}
          finally {
          try {
        rs.close();
        stmt.close();
        conn.close();
          } catch (Exception e)
          {e.printStackTrace();}
          }
     }

   public static void task4(double s, int Size, double confidence) throws IOException{
       File input= new File("system.in" );
       BufferedReader reader= null;
    
       reader= new BufferedReader(new InputStreamReader(new FileInputStream(input)));
       
       String[] get=null;
       String line= null; 
       String[] str= null;
      
       
       String[] temp=null;
       line = reader.readLine();
       str = line.split(" = ");
       get=str[1].split(",");
       String inp1=get[0];
       String inp2=str[2];
       reader.close();
       
       
         ResultSet rs = null;
       Statement stmt = null;
       Connection conn = null;
       DecimalFormat df = new DecimalFormat( "#0.0000" );


        String url = "jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl" ;
        String username = inp1;
        String password = inp2;
    
        try {
       Class. forName("oracle.jdbc.driver.OracleDriver");
       conn =DriverManager. getConnection(url,username,password);
       FileOutputStream fos = new FileOutputStream("system.out.4");
      
      // System. out.println("*************TASK 4**************" );
      
       if(Size<=1){
         System. out.println("Program do not support Size=1 for task 4" );
       } else if (Size>1){
       stmt = conn.createStatement();
      
       for(int i=2;i<=Size;i+=1){
      
         arm. sub2_task4(s, i, confidence);
       }
   //    System. out.println("*************Completed**************" );
       }
      
     
       System.setOut(new PrintStream(fos));

    
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    
       } catch (ClassNotFoundException e)
                             {e.printStackTrace();}
          catch (SQLException e)
                             {e.printStackTrace();}
         finally {
         try {
   //    rs.close();
       stmt.close();
       conn.close();
         } catch (Exception e)
         {e.printStackTrace();}
         }
      
      
   }
  
       public static void getthetable() throws NumberFormatException, IOException{
           File input= new File("system.in" );
           BufferedReader reader= null;
        
           reader= new BufferedReader(new InputStreamReader(new FileInputStream(input)));
           
           String[] get=null;
           String line2= null; 
           String[] str2= null;
          
           
           String[] temp=null;
           line2 = reader.readLine();
           str2 = line2.split(" = ");
           get=str2[1].split(",");
           String inp1=get[0];
           String inp2=str2[2];
           reader.close();
           
         ResultSet rs = null;
           Statement stmt = null;
           Connection conn = null;
           DecimalFormat df = new DecimalFormat( "#0.0000" );


            String url = "jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl" ;
            String username = inp1;
            String password = inp2;
           
            File items= new File("items.dat" );
            File trans= new File("trans.dat" );
            BufferedReader reader1= null;
            BufferedReader reader2= null;
           
            try {
           Class. forName("oracle.jdbc.driver.OracleDriver");
           conn =DriverManager. getConnection(url,username,password);
           stmt = conn.createStatement();
          
          
           //System.out.println("test1");
           reader1= new BufferedReader(new InputStreamReader(new FileInputStream(items)));
           //System.out.println("test2");
           reader2= new BufferedReader(new InputStreamReader(new FileInputStream(trans)));
          
           String line= null;          
           String[] str= null;
          
           rs = stmt.executeQuery( "CREATE TABLE Items(itemid integer, itemname varchar(50))");
           rs = stmt.executeQuery( "CREATE TABLE Trans(transid integer, itemid integer)");

         while((line = reader1.readLine())!= null){ 
               
               str = line.split( ","); 
               int ItemId = Integer.parseInt(str[0]); 
               String ItemName = str[1];
               str[1] = str[1] .replaceAll( "\'", "" ); 
               rs = stmt.executeQuery( "insert into Items(ItemId,ItemName) values("+ItemId+ ","+ItemName+")" ); 
           }

        
         while((line = reader2.readLine())!= null){ 
              
               str = line.split( ","); 
               int transid = Integer.parseInt(str[0]); 
               int itemid = Integer.parseInt(str[1]);                
               rs = stmt.executeQuery( "insert into Trans(Transid,Itemid) values("+transid+","+itemid+ ")"); 
           }        

         
         
    
        

        
           } catch (ClassNotFoundException e)
                                 {e.printStackTrace();}
              catch (SQLException e)
                                 {e.printStackTrace();}
             finally {
             try {
           rs.close();
           stmt.close();
           conn.close();
             } catch (Exception e)
             {e.printStackTrace();}
             }
       }
   public static void droptables() throws IOException{
       File input= new File("system.in" );
       BufferedReader reader= null;
    
       reader= new BufferedReader(new InputStreamReader(new FileInputStream(input)));
       
       String[] get=null;
       String line= null; 
       String[] str= null;
      
       
       String[] temp=null;
       line = reader.readLine();
       str = line.split(" = ");
       get=str[1].split(",");
       String inp1=get[0];
       String inp2=str[2];
       reader.close();
       
       
       ResultSet rs = null;
     Statement stmt = null;
     Connection conn = null;
     DecimalFormat df = new DecimalFormat("#0.0000" );


      String url = "jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl" ;
      String username = inp1;
      String password = inp2;
  
      try {
     Class. forName("oracle.jdbc.driver.OracleDriver" );
     conn =DriverManager. getConnection(url,username,password);
  
  
  
                    
     stmt = conn.createStatement();
     rs = stmt.executeQuery( "Drop table items"  );
    
     rs = stmt.executeQuery( "Drop table trans"  );


  
     } catch (ClassNotFoundException e)
                           {e.printStackTrace();}
        catch (SQLException e)
                           {e.printStackTrace();}
       finally {
       try {
     rs.close();
     stmt.close();
     conn.close();
       } catch (Exception e)
       {e.printStackTrace();}
       }
   }
   public static void readfile() throws IOException{
       File input= new File("system.in" );
       BufferedReader reader= null;
    
       reader= new BufferedReader(new InputStreamReader(new FileInputStream(input)));
       
       String[] get=null;
       String line= null; 
       String[] str= null;
      
       
       String[] temp=null;
       line = reader.readLine();
       str = line.split(" = ");
       get=str[1].split(",");
       String inp1=get[0];
       String inp2=str[2];
       
       
       line = reader.readLine();
       str = line.split(" = ");
       str[1]=str[1].replaceAll("%","");
       double inp3=Double.parseDouble(str[1]);
       line = reader.readLine();
       str = line.split(" = ");
       str[1]=str[1].replaceAll("%","");
       double inp4=Double.parseDouble(str[1]);
       line = reader.readLine();
       str = line.split(" = ");
       str[1]=str[1].replaceAll("%, size","");
       double inp5=Double.parseDouble(str[1]);
       int inp6=Integer.parseInt(str[2]);
       line = reader.readLine();
       str = line.split(" = ");
       str[1]=str[1].replaceAll("%, confidence","");
       double inp7=Double.parseDouble(str[1]);
       str[2]=str[2].replaceAll("%, size", "");
       double inp8=Double.parseDouble(str[2]);
       int inp9=Integer.parseInt(str[3]);
       inp3=inp3/100;
       inp4=inp4/100;
       inp5=inp5/100;
       inp7=inp7/100;

       reader.close();
       
       
       
       System.out.println(inp1);
       System.out.println(inp2);
       System.out.println(inp3);
       System.out.println(inp4);
       System.out.println(inp5);
       System.out.println(inp6);
       System.out.println(inp7);
       System.out.println(inp8);
       System.out.println(inp9);
     
	   
   }
       public static void main (String args []) throws NumberFormatException, IOException{
        
           File input= new File("system.in" );
           BufferedReader reader= null;
        
           reader= new BufferedReader(new InputStreamReader(new FileInputStream(input)));
           
           String[] get=null;
           String line= null; 
           String[] str= null;
          
           
           String[] temp=null;
           line = reader.readLine();
           str = line.split(" = ");
           get=str[1].split(",");
           String inp1=get[0];
           String inp2=str[2];
           
           
           line = reader.readLine();
           str = line.split(" = ");
           str[1]=str[1].replaceAll("%","");
           double inp3=Double.parseDouble(str[1]);
           line = reader.readLine();
           str = line.split(" = ");
           str[1]=str[1].replaceAll("%","");
           double inp4=Double.parseDouble(str[1]);
           line = reader.readLine();
           str = line.split(" = ");
           get=str[1].split("%");
         
           double inp5=Double.parseDouble(get[0]);
           int inp6=Integer.parseInt(str[2]);
           line = reader.readLine();
           str = line.split(" = ");
           get=str[1].split("%");
           
           double inp7=Double.parseDouble(get[0]);
           get=str[2].split("%");
           
           double inp8=Double.parseDouble(get[0]);
           int inp9=Integer.parseInt(str[3]);
           inp3=inp3/100;
           inp4=inp4/100;
           inp5=inp5/100;
           inp7=inp7/100;
           inp8=inp8/100;

           reader.close();
      /*     
           System.out.println(inp1);
           System.out.println(inp2);
           System.out.println(inp3);
           System.out.println(inp4);
           System.out.println(inp5);
           System.out.println(inp6);
           System.out.println(inp7);
           System.out.println(inp8);
           System.out.println(inp9);
       */
           
         arm. getthetable();
            arm. task1(inp3);
            arm.task2(inp4);
           arm. task3(inp5,inp6);
         arm. task4(inp7,inp9, inp8);
           arm. droptables();
        
         
}}
            
