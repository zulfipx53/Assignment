package Assignment;
import java.io.*;
/**
* This class implements the division of file.
*
* @author   Zulfikar Ali
* @version 1.0
* @since 25-03-2018
*/
public class Cutter{
    
  /**
  * The constructor does all the splitting work.
  * @param fileName This is the name of the source file.
  * @param filePath This is the path of the source file.
  * @param preLocation This is the location where the parts of the source file are saved.
  */
    public Cutter(String fileName,String filePath,String preLocation){
    try{

     //Buffer size    
       long maxBuffer=1024;    
     File afile=new File(filePath); 
     InputStream iStream=new FileInputStream(afile);


     long totalBytes=afile.length();
     long parts[]=new long[2];

     //Two parts
     parts[0]=totalBytes/2;
     parts[1]=totalBytes-parts[0];

     /*This loop divides the file into two parts and names each part with orginal file name followed by '_' and part number*/
     for(int i=0;i<2;i++){

       OutputStream oStream=new FileOutputStream(new File(preLocation+"\\"+fileName +"_"+i));

       if(maxBuffer>parts[i]){
       paste(iStream,oStream,parts[i]);
       }
       else{
        long divisions=parts[i]/maxBuffer;
        long remainingPart=parts[i]%maxBuffer;
        for(int j=1;j<=divisions;j++)
          paste(iStream,oStream,maxBuffer);
        if(remainingPart>0)
          paste(iStream,oStream,remainingPart);
       } 

     }    
   
   }
   catch(IOException e){System.out.println("Error");} 
   }
   /*
   * This method assist Cutter constructor in moving bytes from source file into target file
   * with specific number.
   *
   * fi InputStream to the source file
   * fo OutputStream to the target file
   * howMany Number of bytes to move to target file.
   */
   private void paste(InputStream fi,OutputStream fo,long howMany)throws IOException{
        byte[] buffer=new byte[(int)howMany];
        int length=fi.read(buffer);
        if(length!=-1)
            fo.write(buffer,0,length);
   }


}
