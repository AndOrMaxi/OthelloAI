//1h ergasia Texnhth Nohmosynh - Akadhmaiko Etos 2018-2019
//3040185 Toumanidou Andromachi
//3150126 Athanassia Nikolaidou
//3130180 Anargyros Roustemis

import java.util.Scanner;

public class Reversi
{
	public static void main(String[] args)
	{
		ReversiPlayer OPlayer = new ReversiPlayer(4, Board.O);//o ypologisths paizei me to O, ba8os dentrou anazhthshs: 4
		//ReversiPlayerAB OPlayer = new ReversiPlayerAB(2, Board.O);// gia dokimh tou prionismatos a-b
		Board board = new Board();//Dhmiourgia enos tamplo paixnidiou
		Scanner input = new Scanner(System.in);//gia na dinei o an8rwpos tis 8eseis pou 8a paiksei
	
//gia dokimes elegxou or8hs ekteleshs, pername diaforous  disdiastatous pinakes akeraiwn	pou paristanoun tis times sta kelia enos board se proxwrhmenh ekseliksh tou paixnidiou
		
//		board.print();
//		int[][] test ={
//				{-1,-1,-1,-1,-1,-1,-1,-1},
//				{-1,-1,-1,-1,-1,-1,-1,-1},
//				{-1,-1,-1,-1,-1,-1,-1, 1},
//				{-1,-1,-1,-1,-1,-1,-1,-1},
//				{-1,-1,-1,-1,-1,-1, 0, 0},
//				{-1,-1,-1,-1,-1,-1, 0, 1},
//				{-1,-1,-1,-1,-1,-1,-1, 0},
//				{-1,-1,-1,-1,-1,-1,-1,-1},
//				};
//		board.setGameBoard(test);
		//board.print();
		       
		while(!board.isTerminal())//oso den exoume ftasei se termatikh katastash
		{
			board.print();//ektypwse to tamplo
			System.out.println();
			switch (board.getLastLetterPlayed())//elegxos gia to poios paikths epaikse teleytaios
			{
				               
				case Board.X: //An epaikse o X
					
					System.out.println("O moves.");
					
					if(!board.pass(Board.O))//an o O exei na paksei
					{	
						Move OMove = OPlayer.MiniMax(board);//dialegei thn kalyterh kinhsh me ton algori8mo minimax
						board.makeMove(OMove.getRow(), OMove.getCol(), Board.O);//paizei thn kinhsh pou epelekse
						
						System.out.println("O played row "+OMove.getRow()+" col "+ OMove.getCol());
						System.out.println();
						
						break;
					} 
					
				case Board.O:
					
					System.out.println("X moves.");
					
					if(!board.pass(Board.X))//an o X exei na paksei
					{	//perimenoume na dwsei apo to plhktrologio syntetagmenes ths 8eshs pou 8a paiksei			
						System.out.println("Insert row: ");
						int row = input.nextInt();
						
						System.out.println("Insert column: ");
						int col = input.nextInt();
						
						if ( (row >= 0) && (col < 8) && (row < 8) && (col >= 0) )//elegxos an h 8esh einai entos twn oriwn tou board
						{
							if (board.isValidMove(row, col, Board.X))//elegxos an einai egkyrh kinhsh
								board.makeMove(row, col, Board.X);//paizei thn kinhsh pou epelekse
							else							
								System.out.println("Select another position");	//mhnyma la8ous						
						}
						else
						{
							System.out.println("Select a position from (0,0) to (7,7). ");//mhnyma la8ous
						}
						
						System.out.println();
						
						break;					
					}
					
				default:
					
					break;
			}	
		}
		board.print();//ektypwsh tou telikou tamplo
		System.out.println("Terminal");
		//elegxoi gia nikhth
		if (board.getNumX()>board.getNumO())
			System.out.println("X wins!");
		else if(board.getNumX()<board.getNumO())
			System.out.println("O Wins!");
		else
			System.out.println("Draw, tie , whatever...");//isopalia
		
		input.close();	//kleisimo tou scanner
	}
	
}