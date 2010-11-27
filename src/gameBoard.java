import java.awt.Color;
public class gameBoard {
	private static final int ROWS = 8;
	private static final int COLS = 8;
	int numWhiteSeeds=0;
	int numBlackSeeds=0;
	boolean causeFlip=false;
	private static seeds[][] seedBoard = new seeds[ROWS][COLS];
	public gameBoard() {
		newGame();
	}
	
	public void newGame() {
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				seedBoard[r][c] = seeds.EMPTY;
			}
		}
		seedBoard[3][3]=seeds.WHITE;
		seedBoard[4][4]=seeds.WHITE;
		seedBoard[3][4]=seeds.BLACK;
		seedBoard[4][3]=seeds.BLACK;
	}
	
	public seeds[][] getBoard(){
		return seedBoard;
	}
	public boolean legal(int row,int column,seeds player){
		boolean isItLegal=false;
		for(int sRow=-1;sRow<2;sRow++){
			for(int sColumn=-1;sColumn<2;sColumn++){
				if(sRow==0 && sColumn==0) continue;
				int sCheckRow=row+sRow;
				int sCheckColumn=column+sColumn;
				if(sCheckRow>=0 && sCheckColumn>=0 && sCheckRow<=7 && sCheckColumn<=7){
					if(seedBoard[sCheckRow][sCheckColumn]==(player==seeds.BLACK?seeds.WHITE:seeds.BLACK)){
						for(int distance=0;distance<8;distance++){
							int miniCheckRow=row+(distance*sRow);
							int miniCheckColumn=column+(distance*sColumn);
							if(miniCheckRow<0 || miniCheckRow>7 || miniCheckColumn<0 || miniCheckColumn>7) continue;
							if(seedBoard[miniCheckRow][miniCheckColumn]==player){
								for(int goDistance=1;goDistance<distance;goDistance++){
									//goDistance*sRow=Direction
									int flippingRow=row+goDistance*sRow;
									int flippingColumn=column+goDistance*sColumn;
									//Actual Filpping 
									seedBoard[flippingRow][flippingColumn]=player;
								}
								causeFlip=true;
								isItLegal=true;
								break;
							}
							else{
								causeFlip=false;
							}
						}
					}
				}
			}
		}
		return isItLegal;
	}
	public int getSeeds(seeds player){
		int numWhiteSeeds=0;
		int numBlackSeeds=0;
		if(player==seeds.WHITE){
			for(int r=0;r<ROWS;r++){
				for(int c=0;c<COLS;c++){
					if(seedBoard[r][c]==player){
						numWhiteSeeds+=1;
					}
				}
			}
			return numWhiteSeeds;
		}
		else{
			for(int r=0;r<ROWS;r++){
				for(int c=0;c<COLS;c++){
					if(seedBoard[r][c]==player){
						numBlackSeeds+=1;
					}
				}
			}
			return numBlackSeeds;
		}
	}
	public boolean flipCable(){
		return causeFlip;
	}
	
}
