//1h ergasia Texnhth Nohmosynh - Akadhmaiko Etos 2018-2019
//3040185 Toumanidou Andromachi
//3150126 Athanassia Nikolaidou
//3130180 Anargyros Roustemis

import java.util.ArrayList;
import java.util.Random;

public class ReversiPlayerAB//mia prospa8eia na ylopoihsoume to prionisma a-b, alla den paizei svsta o ypologisths
{
   
	private int maxDepth;
	private int playerLetter;
	
	Move a = new Move(Integer.MIN_VALUE);
	Move b = new Move(Integer.MAX_VALUE);
	
	public ReversiPlayerAB()
	{
		maxDepth = 2;
		playerLetter = Board.X;
	}
	
	public ReversiPlayerAB(int maxDepth, int playerLetter)
	{
		this.maxDepth = maxDepth;
		this.playerLetter = playerLetter;
	}

    
	public Move MiniMax(Board board)
	{
        
        if (playerLetter == Board.X)
        {
            return max(new Board(board), 0, a, b);
        }
       
        else
        {
            return min(new Board(board), 0, a, b);
        }
	}

  
	public Move max(Board board, int depth, Move a , Move b)
	{
        Random r = new Random();

       
		if((board.isTerminal()) || (depth == maxDepth))
		{
			Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
			return lastMove;
		}
        
		ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Board.X));
		
		for (Board child : children)
		{	
			
			Move move = min(child, depth + 1,  a ,  b);
            

			if(move.getPlayer() >= a.getPlayer())
			{
					
                if ((move.getPlayer() == a.getPlayer()))
                {
                    if (r.nextInt(2) == 0)
                    {
                        a.setRow(child.getLastMove().getRow());
                        a.setCol(child.getLastMove().getCol());
                        a.setPlayer(move.getPlayer());
                    }
                }
                else 
                {
                    a.setRow(child.getLastMove().getRow());
                    a.setCol(child.getLastMove().getCol());
                    a.setPlayer(move.getPlayer());
                }
			}
			if(a.getPlayer() >= b.getPlayer()) return a;
		}
		return a;
	}

   
	public Move min(Board board, int depth, Move a , Move b)
	{
        Random r = new Random();

		if((board.isTerminal()) || (depth == maxDepth))
		{
			Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
			return lastMove;
		}
		ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Board.O));
		
		for (Board child : children)
		{	
			Move move = max(child, depth + 1, a, b);
			if(move.getPlayer() <= b.getPlayer())
			{
				
                if ((move.getPlayer() == b.getPlayer()))
                {
                    if (r.nextInt(2) == 0)
                    {
                        b.setRow(child.getLastMove().getRow());
                        b.setCol(child.getLastMove().getCol());
                        b.setPlayer(move.getPlayer());
                    }
                }
                else
                {
                        b.setRow(child.getLastMove().getRow());
                        b.setCol(child.getLastMove().getCol());
                        b.setPlayer(move.getPlayer());
                }
            }
			if(a.getPlayer() >= b.getPlayer()) return b;
        }
        return b;
    }
}
