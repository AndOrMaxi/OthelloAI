//1h ergasia Texnhth Nohmosynh - Akadhmaiko Etos 2018-2019
//3040185 Toumanidou Andromachi
//3150126 Athanassia Nikolaidou
//3130180 Anargyros Roustemis

public class Move
{
	//syntetagmenes kinhshs kai o paikths pou thn epaikse
	private int row;
	private int col;
	private int player;
	
	//kataskeyastes
	public Move()
	{
		row = -1;
		col = -1;
		player = 0;
	}
	
	public Move(int row, int col)
	{
		this.row = row;
		this.col = col;
		this.player = -1;
	}
	
	public Move(int value)
	{
		this.row = -1;
		this.col = -1;
		this.player = value;
	}
	
	public Move(int row, int col, int player)
	{
		this.row = row;
		this.col = col;
		this.player = player;
	}
	//getters
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public int getPlayer()
	{
		return player;
	}
	//setters
	public void setRow(int row)
	{
		this.row = row;
	}
	
	public void setCol(int col)
	{
		this.col = col;
	}
	
	public void setPlayer(int player)
	{
		this.player = player;
	}
}
