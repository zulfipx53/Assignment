package Assignment;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
*
* This class implements the gui.It 
* implements a frame with GridLayout, two labels, FileDialog and a panel with FlowLayout. The
* user selects the file with pop up fileDialog and necessary information is shown on the frame.
*
*
* @author  Zulfikar Ali
* @version 1.0
* @since   20-03-2018
*/
public class View{
   
   /*Containers and components of gui*/
   /*
   * Used for frame 
   */
   private Frame mainFrame;
   /*
   * Used for current action in frame
   */
   private Label curAction;
   /*
   * Used for size of the source file
   */
   private Label sizeStatus;
   /*
   *  Used for type of the source file
   */
   private Label typeStatus;
   /*
   * Used for holding the button
   */
   private Panel controlPanel;

   /**
   * Constructor does initial setup.
   */
   public View(){
      prepareGUI();
   }
   /**
   * This is the main method execution starts from here.
   * @param args Unused.
   */
   public static void main(String[] args){
      View  screen = new View();
      screen.showFileDialog();
   }
   /*
   * This method is used to setup the gui. 
   * All labels and the panel are placed into the frame container respective allignments.
   */
   private void prepareGUI(){
      mainFrame = new Frame("Frame");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(4, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      curAction = new Label("Browse a file.");
      curAction.setAlignment(Label.CENTER);
      sizeStatus = new Label();        
      sizeStatus.setAlignment(Label.LEFT);
      sizeStatus.setSize(350,100);
      typeStatus=new Label();
      typeStatus.setAlignment(Label.LEFT);


      controlPanel = new Panel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(curAction);
      mainFrame.add(controlPanel);
      mainFrame.add(typeStatus);
      mainFrame.add(sizeStatus);
      mainFrame.setVisible(true);  
   }

   /*
   * This method is used to create a <h3>FileDialog</h3> and a <h3>Button</h3>.
   * Buttong is added to the panel.
   * Also, an <h3>ActionListener</h3> is added to the button which on click action
   * performs following tasks. 
   * 1) Set the fileDialog to visible(true).
   * 2) Update the text field of <h3>curAction</h3> label.
   * 3) Set text field of <h3>typeStatus</h3> label to file's extension.
   * 4) Set text field of <h3>sizeStatus</h3> label to file's size.
   * 
   * 
   *
   */
   private void showFileDialog(){
      

      final FileDialog fileDialog = new FileDialog(mainFrame,"Select file");
      Button showFileDialogButton = new Button("Browse");
      
      //Action Listener to Button
      showFileDialogButton.addActionListener(new ActionListener() {   
         public void actionPerformed(ActionEvent e) {
            fileDialog.setVisible(true);
            curAction.setText("File Selected :" 
            + fileDialog.getDirectory() + fileDialog.getFile());

             
            //Getting file's extension after '.' and setting typeStatus label
            int dotIndex=fileDialog.getFile().lastIndexOf('.');
            typeStatus.setText("File type :"+fileDialog.getFile().substring(dotIndex+1));

            //Getting the size of file in bytes and setting sizeStatus label
            File temp=new File(fileDialog.getDirectory()+fileDialog.getFile());
            sizeStatus.setText("File Size(Bytes) :"+temp.length());

            //Splitting the file into two 
            Cutter cut=new Cutter(fileDialog.getFile(),fileDialog.getDirectory()+fileDialog.getFile(),"C:");
            
         }
      });

      controlPanel.add(showFileDialogButton);
      mainFrame.setVisible(true);  
   }
}
