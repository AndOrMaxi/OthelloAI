//1h ergasia Texnhth Nohmosynh - Akadhmaiko Etos 2018-2019
//3040185 Toumanidou Andromachi
//3150126 Athanassia Nikolaidou
//3130180 Anargyros Roustemis

import java.util.ArrayList;

public class Board//apeikonizei mia katastash tou paixnidiou
{
   
	public static final int X = 1; //sta8era gia thn timh pou topo8etei o X sto tamplo
	public static final int O = -1;//sta8era gia thn timh pou topo8etei o O sto tamplo
	public static final int EMPTY = 0;//sta8era gia to keno keli
    
    private Move lastMove;//h kinhsh pou ekane o paikths pou epaikse prin
	private int lastLetterPlayed;//o paikths pou epaikse prin
	private int [][] gameBoard;//oles oi times pou yparxoun panw sto tamplo
	private int numX;//to plh8os twn keliwn pou katexei o X
	private int numO;//to plh8os twn keliwn pou katexei o O
	
	//kataskeyastes
	public Board()
	{
		lastMove = new Move();
		lastLetterPlayed = O;
		gameBoard = new int[8][8];//dhmiourgia disdiastatou pinaka akeraiwn 8x8
		
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				gameBoard[i][j] = EMPTY;//arxikopoihsh me kena 
			}
		}
		
		//topo8ethsh twn tessarwn kentrikwn timwn tou arxikou tamplo
		gameBoard[3][3] = X;
		gameBoard[3][4] = O;
		gameBoard[4][3] = O;
		gameBoard[4][4] = X;
		numX=2;//o X katexei 2 kelia
		numO=2;//o O katexei 2 kelia
		
	}
	
	public Board(Board board)
	{
		lastMove = board.lastMove;
		lastLetterPlayed = board.lastLetterPlayed;
		gameBoard = new int[8][8];
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				gameBoard[i][j] = board.gameBoard[i][j];
			}
		}
	}
	
	//getters
	
	public Move getLastMove()
	{
		return lastMove;
	}
	
	public int getLastLetterPlayed()
	{
		return lastLetterPlayed;
	}
	
	public int getNumX()
	{
		return numX;
	}
	
	public int getNumO()
	{
		return numO;
	}
	
	public int[][] getGameBoard()
	{
		return gameBoard;
	}
	
	//setters
	
	public void setLastMove(Move lastMove)
	{
		this.lastMove.setRow(lastMove.getRow());
		this.lastMove.setCol(lastMove.getCol());
		this.lastMove.setPlayer(lastMove.getPlayer());
	}
	
	public void setLastLetterPlayed(int lastLetterPlayed)
	{
		this.lastLetterPlayed = lastLetterPlayed;
	}
	
	public void setNumX(int numX)
	{
		this.numX = numX;
	}
	
	public void setNumO(int numO)
	{
		this.numO = numO;
	}
	
	public void setGameBoard(int[][] gameBoard)
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				this.gameBoard[i][j] = gameBoard[i][j];
			}
		}
	}
	
	
	public void updateAll(int letter)//enhmerwsh tou plh8ous twn keliwn gia ka8e toumparisma diskou tou antipalou
	{
		if (letter == Board.X)
		{
			numX++;
			numO--;
		}
		else
		{
			numX--;
			numO++;
		}	
	}
	
	
	public void makeMove(int row, int col, int letter) //o paikths kanei kinhsh afou exei elex8ei oti einai egkyrh
	{

		gameBoard[row][col] = letter;//grafei to symbolo tou o paikths sthn kenh 8esh
		lastMove = new Move(row, col, letter);//kratame thn kinhsh
		lastLetterPlayed = letter;//kratame ton teleytaio paikth
		updateNum(letter);
		//gia ola ta kelia gyrw tou keliou pou 8elei na paiksei o paikths
		for (int i=row-1; i<= row+1;i++)
		{
			//periorizoume thn anazhthsh entos twn oriwn tou board, gia na mhn bgei out of bounds
			if ( (i >= 0) && (i < 8) )
			{
				for (int j = col-1; j <= col+1; j++)
				{
					if ( (j >= 0) && (j < 8) )
					{
						//elegxoume an h 8esh kalyptetai apo ton antipalo
						if ( (gameBoard[i][j] != EMPTY)&&(gameBoard[i][j] != letter) )
						{
							//tote koitame se poia katey8ynsh brisketai se sxesh me th 8esh pou 8eloyme na paiksoume
							
							//an einai sthn idia grammh kai deksia ths 8eshs
							if ( (i == row) && (j > col) )
							{
								int l = j;
								// oso den briskoume adeio koyti kai den ksepername ta oria tou tamplo, synexizoume
								while ( (l < 8) && (gameBoard[i][l] != EMPTY))
								{
									//an broume omoio "Gramma" me ton paixth poy paizei 
									if(gameBoard[i][l] == letter)
									{
										//"anapodogyrise" ta endiamesa kelia 
										for (int p=l; p>j;)	
										{
											
											gameBoard[i][--p] = letter;
											updateAll(letter);
										}
										
										break;
									}
									l++;
								}
							}
							
							//an einai sthn idia grammh kai aristera ths 8eshs
							if ((i == row) && (j < col))
							{
								int l=j;
								
								while ( (l >= 0) && (gameBoard[i][l] != EMPTY))
								{
									if(gameBoard[i][l] == letter)
									{
										for (int p=l; p<j;)	
										{
											gameBoard[i][++p] = letter;
											updateAll(letter);
										}
										
										break;
									}								
									l--;
								}
							}
							
							//an einai sthn idia sthlh kai anw ths 8eshs
							if ( (i < row) && (j == col) )
							{
								int k=i;
								
								while ( (k >= 0) && (gameBoard[k][j] != EMPTY))
								{
									if (gameBoard[k][j] == letter)
									{
										for (int p = k; p < i;)	
										{
											gameBoard[++p][j] = letter;
											updateAll(letter);
										}
										
										break;
									}								
									k--;
								}
							}
							
							//an einai sthn idia sthlh kai katw ths 8eshs
							if ((i > row) && (j == col))
							{
								int k = i;
								
								while ( (k < 8) && (gameBoard[k][j] != EMPTY) )
								{
									if(gameBoard[k][j] == letter)
									{
										for (int p = k; p > i;)	
										{
											gameBoard[--p][j] = letter;
											updateAll(letter);
										}
										
										break;
									}	
										
									k++;
								}
							}
							
							//an einai anw aristera diagwnia
							if ( (i < row) && (j < col) )
	 						{
								int k = i;
								int l = j;
								
								while ( (k >= 0)  && (l >= 0) && (gameBoard[k][l] != EMPTY) )
								{
									if (gameBoard[k][l] == letter) 
									{
										for (int p = k; p < i;)	
										{
											
											gameBoard[++p][++l] = letter;
											updateAll(letter);
											
										}
										
										break;
									}								
									k--;
									l--;
								}
							}
							
							//an einai anw deksia diagwnia
							if ( (i < row) && ( j > col) )
							{
								int k = i;
								int l = j;
								while ((k>=0)  && (l<8)&& (gameBoard[k][l] != EMPTY))
								{
									if(gameBoard[k][l] == letter)
									{
										for (int p=k; p<i;)	
										{
											gameBoard[++p][--l] = letter;
											updateAll(letter);
											
										}
										
										break;
									}										
									k--;
									l++;
								}
							}
							
							//an einai katw aristera diagwnia
							if ( (i > row) && (j < col) )
							{
								int k=i;
								int l=j;
								
								while ( (k < 8)  && (l >= 0) && (gameBoard[k][l] != EMPTY) )
								{
									if (gameBoard[k][l] == letter)
									{
										for (int p = k; p > i;)	
										{
											gameBoard[--p][++l] = letter;
											updateAll(letter);										
										}
										
										break;
									}
									
									k++;
									l--;
								}
							}
							
							//an einai katw deksia diagwnia
							if ( (i > row) && ( j > col) )
							{
								int k = i;
								int l = j;
								
								while ( (k < 8)  && (l < 8) && (gameBoard[k][l] != EMPTY))
								{
									if(gameBoard[k][l] == letter)
									{
										for (int p = k; p > i;)	
										{
											gameBoard[--p][--l] = letter;
											updateAll(letter);
										}
										
										break;
									}	
									
									k++;
									l++;
								}
							}	
						}
					}
				}
			}
		}		
	}
	
	public void updateNum(int letter)//aykshsh tou plh8ous kata ena gia th 8esh pou topo8ethse o paikths neo disko
	{
		if (letter==Board.X)
			numX++;
		else		
			numO++;	
	}
	
	public boolean pass(int letter)//otan den yparxei dia8esimh kinhsh gia ton paikth, kanei paso aytomata
	{
		for (int row = 0; row < 8; row++)
		{
			for (int col = 0; col < 8; col++)
			{
				if (isValidMove(row, col, letter))
					return false;			
			}	
		}
		
		System.out.println("No valid moves for player.\n");
		this.setLastLetterPlayed(letter);
		
		return true;
	}
	

	public boolean isValidMove(int row, int col, int letter) //elegxos an h kinhsh pou 8elei na paiksei o paikths einai epitrepth
	{
		if ((row == -1) || (col == -1) || (row > 7) || (col > 7))//prepei na einai row metaksy 0-7 kai column metaksy 0-7
		{
			return false;
		}
		
		if (gameBoard[row][col] != EMPTY)//prepei h 8esh pou 8elei na paiksei o paikths na einai kenh
		{
			return false;
		}
		//gia ola ta kelia gyrw tou keliou pou 8elei na paiksei o paikths
	
		for (int i = row-1; i <= row+1; i++)
		{
			//periorizoume thn anazhthsh entos twn oriwn tou board, gia na mhn bgei out of bounds
			if ( (i >= 0) && (i < 8) )
			{
				for (int j = col-1; j <= col+1; j++)
				{
					if ( (j >= 0) && (j < 8))
					{
						//elegxoume an h 8esh kalyptetai apo ton antipalo
						if ( (gameBoard[i][j] != EMPTY) && (gameBoard[i][j] != letter) )
						{
							//tote koitame se poia katey8ynsh brisketai se sxesh me th 8esh pou 8eloyme na paiksoume
							
							//an einai sthn idia grammh kai deksia ths 8eshs
							if ( (i == row) && (j > col) )
							{
								int k = j;
								// oso den briskoume adeio koyti kai den ksepername ta oria tou tamplo, synexizoume
								while ( (k < 8) && (gameBoard[i][k] != EMPTY) )
								{	
									//an brei "gramma" idio me ton paikth, epistrefei true
									if (gameBoard[i][k] == letter)
										return true;	
										
									k++;
								}
							}
							
							//an einai sthn idia grammh kai aristera ths 8eshs
							if ( (i == row) && (j < col) )
							{
								int k = j;
								
								while ( (k >= 0) && (gameBoard[i][k] != EMPTY) )
								{
									if (gameBoard[i][k] == letter)
										return true;	
										
									k--;
								}
							}
							
							//an einai sthn idia sthlh kai anw ths 8eshs
							if ( (i < row) && (j == col) )
							{
								int k = i;
								
								while ( (k >= 0)  && (gameBoard[k][j] != EMPTY) )
								{
									if (gameBoard[k][j] == letter) 
										return true;	
										
									k--;
								}
							}
							
							//an einai sthn idia sthlh kai katw ths 8eshs
							if ( (i > row) && (j == col) )
							{
								int k=i;
								
								while ( (k < 8) && (gameBoard[k][j] != EMPTY) )
								{
									if (gameBoard[k][j] == letter)
										return true;	
										
									k++;
								}
							}
							
							//an einai anw aristera diagwnia
							if ( (i < row) && (j < col) )
							{
								int k=i;
								int l=j;
								
								while ( (k >= 0) && (l >= 0) && (gameBoard[k][l] != EMPTY) )
								{
									if (gameBoard[k][l] == letter)
										return true;	
										
									k--;
									l--;
								}
							}
							
							//an einai anw deksia diagwnia
							if ( (i < row) && (j > col) )
							{
								int k=i;
								int l=j;
								
								while ( (k >= 0)  && (l < 8) && (gameBoard[k][l] != EMPTY))
								{
									if (gameBoard[k][l] == letter)
										return true;	
										
									k--;
									l++;
								}
							}
							
							//an einai katw aristera diagwnia
							if ( (i > row) && (j < col) )
							{
								int k=i;
								int l=j;
								
								while ( (k < 8)  && (l >= 0) && (gameBoard[k][l] != EMPTY) )
								{
									if (gameBoard[k][l] == letter)
										return true;	
										
									k++;
									l--;
								}
							}
							
							//an einai katw deksia diagwnia
							if ( (i > row) && (j > col) )
							{
								int k=i;
								int l=j;
								
								while ( (k < 8)  && (l < 8) && (gameBoard[k][l] != EMPTY) )
								{
									if (gameBoard[k][l] == letter)
										return true;	
										
									k++;
									l++;
								}
							}						
						}	
					}
				}
			}
		}
			
		return false;
	}


	public ArrayList<Board> getChildren (int letter)//pairnw ta paidia ths katastashs, dhladh oles tis dynates katastaseis pou mporoyn na prokypsoyn an kanei o antipalos ka8e dynath kinhsh
	{
		ArrayList<Board> children = new ArrayList<Board>();//ta topo8etw se mia arraylist
		
		for (int row = 0; row < 8; row++)
		{
			for (int col = 0; col < 8; col++)
			{
				if (isValidMove(row, col, letter))
				{
					Board child = new Board(this);
					child.makeMove(row, col, letter);
					children.add(child);
				}
			}
		}
		
		return children;
	}

	
	public int evaluate()//apotimhsh katastash me syn8esh eyretikwn
	{
		int value;//h synolikh aksia ths katastashs
		int corners=0;
		int edges=0;
		
		corners = gameBoard[0][0]+gameBoard[7][0]+gameBoard[0][7]+gameBoard[7][7];//a8roish ths aksias twn 4 gwniwn
	
		//a8roish twn aksiwn twn perimetrikwn keliwn ektos apo tis gwnies
		for (int j=1;j<7;j++)
			edges+=gameBoard[0][j];
		for (int i=1;i<7;i++)
			edges+=gameBoard[i][0];
		for (int j=1;j<7;j++)
			edges+=gameBoard[7][j];
		for (int i=1;i<7;i++)
			edges+=gameBoard[i][7];
		//synolikh apotimhsh: 
		//plh8os keliwn tou X meion to plh8os keliwn tou O (dhladh, ousiastika to a8roisma twn aksiwn olwn twn keliwn (afou exoun times 1 ta X, -1 ta O kai 0 ta kena
		//syn 10 fores thn aksia twn gwniwn
		//syn 2 fores thn aksia twn ypoloipwn perimetrikwn
		value = ((numX - numO) + 10 * corners + 2 * edges);
		
		return value;
	}

   
    public boolean isTerminal()//elegxos an briskomaste se termatikh katastash
    {
		for (int row = 0; row < 8; row++)
		{
			for(int col = 0; col < 8; col++)
			{
	    		if (gameBoard[row][col] == EMPTY)
				{
	    			if ( isValidMove(row,col,Board.X) || isValidMove(row,col,Board.O) )
	    				return false;		
	    		}
    		}
		}
		//epistrefei true an den exei na paiksei egkyrh kinhsh oute o X, oute o O
		return true;
    }

    //Prints the board
	public void print()//ektypwsh tou tamplo tou paixnidiou sthn trexousa katastash
	{
		
		System.out.println("     0   1   2   3   4   5   6   7  ");
		System.out.println("   ---------------------------------");
		
		for (int row = 0; row < 8; row++)
		{
			System.out.print(" "+row+" |");
			
			for(int col = 0; col < 8; col++)
			{
				switch (gameBoard[row][col])
				{
					case X:
						System.out.print(" X |");
						break;
					case O:
						System.out.print(" O |");
						break;
					case EMPTY:
						System.out.print("   |");
						break;
					default:
						break;
				}
			}
			
			System.out.println();
			System.out.println("   ---------------------------------");
			
		}
		
		System.out.println();
		System.out.println("   Score: X:" + this.numX + " O:" + this.numO);
		System.out.println("   ---------------------------------\n");
	}
}
