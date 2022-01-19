import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main {
  
  private static final int GRID_SIZE = 9;
  
  private static JTextField[] txtField;
  
  private static JFrame myFrame;
  
  public static void main(String[] args) {
	  
	int[][] board = {
	            {7, 0, 2, 0, 5, 0, 6, 0, 0},
	            {0, 0, 0, 0, 0, 3, 0, 0, 0},
	            {1, 0, 0, 0, 0, 9, 5, 0, 0},
	            {8, 0, 0, 0, 0, 0, 0, 9, 0},
	            {0, 4, 3, 0, 0, 0, 7, 5, 0},
	            {0, 9, 0, 0, 0, 0, 0, 0, 8},
	            {0, 0, 9, 7, 0, 0, 0, 0, 5},
	            {0, 0, 0, 2, 0, 0, 0, 0, 0},
	            {0, 0, 7, 0, 4, 0, 2, 0, 3} 
	        };
	  
	myFrame = new JFrame();
	
	txtField = new JTextField[82];
    loadBoard(board);
	
	JButton newButton = new JButton("Solve me!");
	newButton.setBounds(130,400,100, 40);
	newButton.addActionListener(new ActionListener() 
	{
		public void actionPerformed(ActionEvent e){  
			setBoard(board);
            solveBoard(board);
            printBoard(board);
        }  
	});
	
	myFrame.add(newButton);
	myFrame.setSize(400,500);
	myFrame.setLayout(null);
	myFrame.setVisible(true);

	printBoard(board);
    
	/*
	 * printBoard(board);
	 * 
	 * if (solveBoard(board)) { System.out.println("Solved successfully!"); } else {
	 * System.out.println("Unsolvable board :("); }
	 */
    

    
  }
  
  private static void setBoard(int[][] board) {
		int o=1;
	    for (int m=0; m<9;m++)
	    	for (int n=0;n<9;n++) {
	    		//System.out.println(board[m][n]);
	    		board[m][n]=Integer.parseInt(txtField[o++].getText());
	    		System.out.println(board[m][n]);
	    	}
  }

private static void loadBoard(int[][] board) {
	int o=1;
    for (int m=0; m<9;m++)
    	for (int n=0;n<9;n++)
    		//System.out.println(board[m][n]);
    		txtField[o++] = new JTextField(""+board[n][m]+"");
    
	int j=1;
	int k=1;
	for(int i=1; i<82; i++) {
		txtField[i].setBounds(30*k,100*j/4, 20,20);
		if (i%9==0) {
			k=1;j++;
		}
		else {
			k++;
		}
		myFrame.add(txtField[i]);
  	}
}
  
  
  private static void printBoard(int[][] board) {
    for (int row = 0; row < GRID_SIZE; row++) {
      if (row % 3 == 0 && row != 0) {
        System.out.println("-----------");
      }
      for (int column = 0; column < GRID_SIZE; column++) {
        if (column % 3 == 0 && column != 0) {
          System.out.print("|");
        }
        System.out.print(board[row][column]);
      }
      System.out.println();
    }
    
    int o=1;
    for (int m=0; m<9;m++)
    	for (int n=0;n<9;n++)
    		//System.out.println(board[m][n]);
    		txtField[o++].setText(""+board[m][n]+"");
  }


  private static boolean isNumberInRow(int[][] board, int number, int row) {
    for (int i = 0; i < GRID_SIZE; i++) {
      if (board[row][i] == number) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean isNumberInColumn(int[][] board, int number, int column) {
    for (int i = 0; i < GRID_SIZE; i++) {
      if (board[i][column] == number) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
    int localBoxRow = row - row % 3;
    int localBoxColumn = column - column % 3;
    
    for (int i = localBoxRow; i < localBoxRow + 3; i++) {
      for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
        if (board[i][j] == number) {
          return true;
        }
      }
    }
    return false;
  }
  
  private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
    return !isNumberInRow(board, number, row) &&
        !isNumberInColumn(board, number, column) &&
        !isNumberInBox(board, number, row, column);
  }
  
  private static boolean solveBoard(int[][] board) {
    for (int row = 0; row < GRID_SIZE; row++) {
      for (int column = 0; column < GRID_SIZE; column++) {
        if (board[row][column] == 0) {
          for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
            if (isValidPlacement(board, numberToTry, row, column)) {
              board[row][column] = numberToTry;
              
              if (solveBoard(board)) {
                return true;
              }
              else {
                board[row][column] = 0;
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }
  
  
  
}


