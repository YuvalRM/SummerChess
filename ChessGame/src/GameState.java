import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GameState {
	private AbsPiece [][] theGame= new AbsPiece[8][8];
	private Color turn=Color.White;
	private Set<AbsPiece> whitePiece=new HashSet<AbsPiece>();
	private Set<AbsPiece> blackPiece=new HashSet<AbsPiece>();
	private Pawn twoSteps;
	public GameState() {
		theGame[0][0]=new Rook(Color.White,0,0);
		theGame[0][1]=new Knight(Color.White,0,1);
		theGame[0][2]=new Bishop(Color.White,0,2);
		theGame[0][3]=new Queen(Color.White,0,3);
		theGame[0][4]=new King(Color.White,0,4);
		theGame[0][5]=new Bishop(Color.White,0,5);
		theGame[0][6]=new Knight(Color.White,0,6);
		theGame[0][7]=new Rook(Color.White,0,7);
		theGame[7][0]=new Rook(Color.Black,7,0);
		theGame[7][1]=new Knight(Color.Black,7,1);
		theGame[7][2]=new Bishop(Color.Black,7,2);
		theGame[7][3]=new Queen(Color.Black,7,3);
		theGame[7][4]=new King(Color.Black,7,4);
		theGame[7][5]=new Bishop(Color.Black,7,5);
		theGame[7][6]=new Knight(Color.Black,7,6);
		theGame[7][7]=new Rook(Color.Black,7,7);
		for (int i=0;i<8;i++) {
			theGame[1][i]=new Pawn(Color.White,1,i);
			theGame[6][i]=new Pawn(Color.Black,6,i);
			for(int j=2;j<6;j++) {
				theGame[j][i]=new NoPiece();
			}
		}
		this.putInLists();

	}
	private void putInLists() {
        whitePiece=new HashSet<AbsPiece>();
		blackPiece=new HashSet<AbsPiece>();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(theGame[i][j].getColor()==Color.Black) {
					blackPiece.add(theGame[i][j]);
				}
				if(theGame[i][j].getColor()==Color.White) {
					whitePiece.add(theGame[i][j]);
				}
			}
		}
	}
	public String toString() {
		String ret="";
		for(int i=7;i>=0;i--) {
			AbsPiece[] row=theGame[i];
			for(AbsPiece p:row) {
				ret+=(p.toString()+"\t|");
			}
			ret+="\n";
		}
		return ret;
	}
	public AbsPiece[][] getTheGame(){
		return this.theGame;
	}
	public void changeTurn() {
		turn=this.turn.opposite();
	}
	public Color getTurn() {
		return this.turn;
	}
	public boolean isCheck() {
		Set<AbsPiece> ourpieces;
		Set<AbsPiece> ourpieces2;
		GameState copy=this.copy();
		if(this.turn==Color.Black) {
			ourpieces=this.whitePiece;
			ourpieces2=this.blackPiece;
		}
		else {
			ourpieces=this.blackPiece;
			ourpieces2=this.whitePiece;
		}
		AbsPiece king=null;
		for(AbsPiece p: ourpieces2) {
			if(p instanceof King) {
				king=p.copy();
				break;
			}
			
		}
		int row=king.getRow();
		int col=king.getCol();


		for(AbsPiece p: ourpieces) {
			
			if(p.CanMove(row, col, copy)) {
				return true;
			}
		}
		return false;
		
	}
    public boolean inputValid(String move) {
    	if(move.equals("O-O")||move.equals("O-O-O")) {
    		return true;
    	}
    	Set<Character> legalRows=new HashSet<Character>();
    	Collections.addAll(legalRows, 'a','b','c','d','e','f','g','h');
    	Set<Character> legalCols=new HashSet<Character>();
    	Collections.addAll(legalCols, '1','2','3','4','5','6','7','8');
    	Set<Character> legalPieces=new HashSet<Character>();
    	Collections.addAll(legalPieces, 'K','Q','N','R','B');
    	if(move.length()==2) {
    		if(legalRows.contains(move.charAt(0))&&legalCols.contains(move.charAt(1))) {
    			if(turn==Color.White && (move.charAt(1)=='1'||move.charAt(1)=='2')) {
    				return false;
    			}
    			else if(turn==Color.Black && (move.charAt(1)=='7'||move.charAt(1)=='8')) {
    				return false;
    			}
    			return true;
    		}
    		return false;
    		
    	}
    	if(move.length()==3) {
    		if(legalRows.contains(move.charAt(1))&&legalCols.contains(move.charAt(2))&&legalPieces.contains(move.charAt(0))) {
    			return true;
    		}
    		return false;
    		
    	}
    	if(move.length()==4) {
    		if(legalRows.contains(move.charAt(0))&&legalCols.contains(move.charAt(1))&&legalRows.contains(move.charAt(2))&&legalCols.contains(move.charAt(3))) {
    			if(turn==Color.White && (move.charAt(1)=='1'||move.charAt(3)=='2'||move.charAt(3)=='1')) {
    				return false;
    			}
    			else if(turn==Color.Black && (move.charAt(1)=='8'||move.charAt(3)=='8'||move.charAt(3)=='7')) {
    				return false;
    			}
    			return true;
    		}
    		return false;
    	}
    	if(move.length()==5) {
    		if(legalRows.contains(move.charAt(3))&&legalCols.contains(move.charAt(4))&&legalRows.contains(move.charAt(1))&&legalCols.contains(move.charAt(2))&&legalPieces.contains(move.charAt(0))) {
    			return true;
    		}
    		return false;
    	}
    	return false;
    }
    public void discardPiece(AbsPiece p) {
    	if(turn==Color.Black) {
    		whitePiece.remove(p);
    	}
    	else if(turn==Color.White) {
    		blackPiece.remove(p);
    	}
    	this.putInLists();
    }
    public boolean anyMove() {
    	Set<AbsPiece> ourpieces;
    	if(this.turn==Color.Black) {
    		ourpieces=this.blackPiece;
    	}
    	else {
    		ourpieces=this.whitePiece;
    	}
    	for(AbsPiece p:ourpieces) {
    		for(int i=0;i<7;i++) {
    			for(int j=0;j<7;j++) {
    				if(p.CanMove(i, j, this)) {
    					GameState copy=this.copy();
    					Set<AbsPiece> ourpieces2;
    			    	if(this.turn==Color.Black) {
    			    		ourpieces2=copy.blackPiece;
    			    	}
    			    	else {
    			    		ourpieces2=copy.whitePiece;
    			    	}
    			    	AbsPiece newp=null;
						for(AbsPiece r:ourpieces2) {
							if (r.equals(p)) {
								newp=r;
								break;
							}
						}
    					newp.moveTo(i, j, copy);
    					if(!copy.isCheck()) {
    						return true;
    					}
    				}
    			}
    		}
    	}
    	return false;
    }
    /**@pre: this.inputVlid(move)==true
     * @param move
     * @return 
     */
    public Optional<GameState> moveValid(String move) {
    	GameState toChange=this.copy();
    	Set<AbsPiece> ourPieces;
    	if(toChange.turn==Color.White) {
    		ourPieces=toChange.whitePiece;
    	}
    	else {
    	    ourPieces=toChange.blackPiece;
    	}
    	int col=-1;
    	int row=-1;
    	int ncol=-1;
    	int nrow=-1;
    	AbsPiece there= new NoPiece();
    	boolean moved=false;
    	if((move.equals("O-O") || move.equals("O-O-O"))&&!this.isCheck()) {
    		AbsPiece rook;
    		AbsPiece king;
    		Boolean inBetween=false;
    		int a=0;//indicates of whether it's a big or small castle
    		int b=0;
    		int k=0;//line we are working on
    		int adder=1;
    		if(toChange.turn==Color.Black) {
    			k=7;
    		}
    		if(move.equals("O-O")) {
    			a=4;
    			b=7;
    			rook=toChange.theGame[k][7];
    		}
    		else {
    			a=0;
    			b=4;
    			rook=toChange.theGame[k][0];
    			adder=-1;
    		}
    		
    		
    		king=toChange.theGame[k][4];
    		for (int i=a+1;i<b;i++) {
    			if(toChange.theGame[k][i].getColor()!=Color.Nothing) {
    				inBetween=true;
    			}
    		}
    		GameState check=this.copy();
    		AbsPiece checkKing=check.theGame[k][4];
    		checkKing.moveTo(k, 4+adder, check);
    			if(check.isCheck()) {
    				inBetween=true;
    		}
    		if(!rook.getMoved() && !king.getMoved() && !inBetween){
    			rook.moveTo(k, 4+adder, toChange);
    			king.moveTo(k, 4+2*adder, toChange);
    			moved=true;
    		}
    		
    		
    	}
    	else if(move.length()==2) {
        	col=move.charAt(0)-'a';
    	    row=Character.getNumericValue(move.charAt(1))-1;
    		boolean found=false;
    		boolean tooMuch=false;
    		for(AbsPiece p:ourPieces) {
    			if(p instanceof Pawn && p.CanMove(row, col, toChange)) {
    				if(found) {
    					tooMuch=true;
    				}
    				there=p;
    				found=true;
    			}
    		}
    		if(!tooMuch&&found) {
    			there.moveTo(row, col, toChange);
    			moved=true;
    			
    		}
    		
    	}
    	
    	else if(move.length()==3) {
        	col=move.charAt(1)-'a';
    	    row=Character.getNumericValue(move.charAt(2))-1;
    		boolean found=false;
    		boolean tooMuch=false;
    		if(move.charAt(0)=='N') {
    			for(AbsPiece p:ourPieces) {
    				if(p instanceof Knight && p.CanMove(row, col, toChange)) {
    					if(found) {
    						tooMuch=true;
    					}
    					there=p;
    					found=true;
    				}
    			}
    		}
    		else if(move.charAt(0)=='R') {
    			for(AbsPiece p:ourPieces) {
    				if(p instanceof Rook && p.CanMove(row, col, toChange)) {
    					if(found) {
    						tooMuch=true;
    					}
    					there=p;
    					found=true;
    				}
    			}
    		}
    		else if(move.charAt(0)=='B') {
    			for(AbsPiece p:ourPieces) {
    				if(p instanceof Bishop && p.CanMove(row, col, toChange)) {
    					if(found) {
    						tooMuch=true;
    					}
    					there=p;
    					found=true;
    				}
    			}
    		}
    		else if(move.charAt(0)=='Q') {
    			for(AbsPiece p:ourPieces) {
    				if(p instanceof Queen && p.CanMove(row, col, toChange)) {
    					if(found) {
    						tooMuch=true;
    					}
    					there=p;
    					found=true;
    				}
    			}
    		}
    		else if(move.charAt(0)=='K') {
    			for(AbsPiece p:ourPieces) {
    				if(p instanceof King && p.CanMove(row, col, toChange)) {
    					if(found) {
    						tooMuch=true;
    					}
    					there=p;
    					found=true;
    				}
    			}
    		}
    			
    		
    		if(!tooMuch&&found) {
    			there.moveTo(row, col, toChange);
    			moved=true;
    			
    		}
    	}
    	else if(move.length()==4) {
    		col=move.charAt(0)-'a';
    	    row=Character.getNumericValue(move.charAt(1))-1;
    	    ncol=move.charAt(2)-'a';
    	    nrow=Character.getNumericValue(move.charAt(3))-1;
    	    there=toChange.theGame[row][col];
    	    if(there.getColor()==toChange.turn&&there instanceof Pawn && there.CanMove(nrow, ncol, toChange)) {
    	    	there.moveTo(nrow, ncol, toChange);
    			moved=true;
    	    }
    	}
    	else if(move.length()==5) {
    		char type=move.charAt(0);
    		col=move.charAt(1)-'a';
    	    row=Character.getNumericValue(move.charAt(2))-1;
    	    ncol=move.charAt(3)-'a';
    	    nrow=Character.getNumericValue(move.charAt(4))-1;
    	    there=toChange.theGame[row][col];
    	    boolean correctType=(there instanceof Rook && type=='R')||
    	    		(there instanceof Queen && type=='Q')||
    	    		(there instanceof Knight && type=='N')||
    	    		(there instanceof King && type=='K')||
    	    		(there instanceof Bishop && type=='B');
    	    if(correctType && there.CanMove(nrow, ncol, toChange)) {
    	    	there.moveTo(nrow, ncol, toChange);
    			moved=true;
    	    }
    	}
    	
    	
    	if(!toChange.isCheck()&&moved) {
    		return Optional.of(toChange);
    	}
		return Optional.empty();
    	
    }
    public GameState copy() {
    	GameState ret=new GameState();
    	for(int i=0;i<8;i++) {
    		for(int j=0;j<8;j++) {
    			ret.theGame[i][j]=this.theGame[i][j].copy();
    		}
    	}
    	ret.putInLists();
    	ret.turn=this.turn;
    	if(this.twoSteps !=null) {
    		ret.twoSteps=(Pawn) this.twoSteps.copy();
    	}
    	else {
    		ret.twoSteps=null;
    	}
    	return ret;
    }
	public Pawn getTwoSteps() {
		return twoSteps;
	}
	public void setTwoSteps(Pawn twoSteps) {
		this.twoSteps = twoSteps;
	}
	public Optional<Pawn> promo() {
		Set<AbsPiece> ourpieces;
    	if(this.turn==Color.Black) {
    		ourpieces=this.blackPiece;
    	}
    	else {
    		ourpieces=this.whitePiece;
    	}
    	for(AbsPiece p: ourpieces) {
    		if(p instanceof Pawn && (p.getRow()==7 || p.getRow()==0)){
    			return Optional.of((Pawn)p);
    		}
    	}
    	return Optional.empty();
		
	}
}
