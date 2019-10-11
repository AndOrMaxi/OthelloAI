//1h ergasia Texnhth Nohmosynh - Akadhmaiko Etos 2018-2019
//3040185 Toumanidou Andromachi
//3150126 Athanassia Nikolaidou
//3130180 Anargyros Roustemis

import java.util.ArrayList;
import java.util.Random;

public class ReversiPlayer
{
   
	private int maxDepth;//ba8os dentrou anazhthshs
	private int playerLetter;//symbolo tou paikth 
	
	public ReversiPlayer()
	{
		maxDepth = 2;
		playerLetter = Board.X;
	}
	
	public ReversiPlayer(int maxDepth, int playerLetter)
	{
		this.maxDepth = maxDepth;
		this.playerLetter = playerLetter;
	}

   
	public Move MiniMax(Board board)//algori8mos minimax, an paizei o X kalei th max(), an paizei o O kalei th min() (emeis exoume balei o ypologisths na einai o O panta,ara mono ayto kalei th Minimax
	{
        if (playerLetter == Board.X)
        {
            return max(new Board(board), 0);
        }
       
        else
        {
            return min(new Board(board), 0);
        }
	}

    
	public Move max(Board board, int depth)
	{
        Random r = new Random();

		if((board.isTerminal()) || (depth == maxDepth))//termatikh syn8hkh
		{
			Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
			return lastMove;
		}
        //pairnw ta paidia tou board
		ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Board.X));
		Move maxMove = new Move(Integer.MIN_VALUE);
		for (Board child : children)
		{
            
			Move move = min(child, depth + 1);
           
			if(move.getPlayer() >= maxMove.getPlayer())
			{
                if ((move.getPlayer() == maxMove.getPlayer()))
                {
                    
                    if (r.nextInt(2) == 0)//gia na epileksei random kinhsh
                    {
                    	
                        maxMove.setRow(child.getLastMove().getRow());
                        maxMove.setCol(child.getLastMove().getCol());
                        maxMove.setPlayer(move.getPlayer());
                    }
                }
                else//an to min einai megalytero apo th mikroterh dynath timh, kane ayto maxMove (xwris prionisma)
                {
                    maxMove.setRow(child.getLastMove().getRow());
                    maxMove.setCol(child.getLastMove().getCol());
                    maxMove.setPlayer(move.getPlayer());
                }
			}
		}
		return maxMove;
	}

    //antistixa leitourgei kai o min
	public Move min(Board board, int depth)
	{
        Random r = new Random();

		if((board.isTerminal()) || (depth == maxDepth))
		{
			Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
			return lastMove;
		}
		ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Board.O));
		Move minMove = new Move(Integer.MAX_VALUE);
		for (Board child : children)
		{
			Move move = max(child, depth + 1);
			if(move.getPlayer() <= minMove.getPlayer())
			{
                if ((move.getPlayer() == minMove.getPlayer()))
                {
                    if (r.nextInt(2) == 0)
                    {
                        minMove.setRow(child.getLastMove().getRow());
                        minMove.setCol(child.getLastMove().getCol());
                        minMove.setPlayer(move.getPlayer());
                    }
                }
                else
                {
                        minMove.setRow(child.getLastMove().getRow());
                        minMove.setCol(child.getLastMove().getCol());
                        minMove.setPlayer(move.getPlayer());
                }
            }
        }
        return minMove;
    }
}
