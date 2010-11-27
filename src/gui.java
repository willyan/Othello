import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class gui {
	seeds player=seeds.BLACK;
	seeds playerWhite=seeds.WHITE;
	seeds playerBlack=seeds.BLACK;
	
//Variables	
	int gameStatus=0;
	int whiteWins=0;
	int blackWins=0;
	gameBoard board=new gameBoard();
	JButton btnNewGame=new JButton();
	JButton btnPass=new JButton();
//Labels
	JLabel lblCurrentPlayer=new JLabel("Current Player : ",JLabel.LEFT);
	JLabel lblCurrentPlayerName=new JLabel("Null");
	JLabel lblBlackOrWhite=new JLabel();
	JLabel lblBlack=new JLabel("Black : ");
	JLabel lblBlackPieces=new JLabel("None");
	JLabel lblWhite=new JLabel("White : ");
	JLabel lblWhitePieces=new JLabel("None");
	JLabel lblWhiteWin=new JLabel("White Wins");
	JLabel lblBlackWin=new JLabel("Black Wins");
	JLabel lblWhiteWins=new JLabel("0",JLabel.CENTER);
	JLabel lblBlackWins=new JLabel("0",JLabel.CENTER);
	JLabel[] lblList={lblCurrentPlayer,lblCurrentPlayerName,lblBlack,lblBlackOrWhite,
	                  lblBlack,lblBlackPieces,lblWhite,lblWhitePieces,lblWhiteWins,
	                  lblWhiteWin,lblBlackWin,
	                  lblBlackWins};
	//Panels
	JPanel pnlPlayer=new JPanel();
	JPanel pnlGameControls=new JPanel();
	JPanel pnlMove=new JPanel();
	JPanel pnlWhite=new JPanel(new FlowLayout());
	JPanel pnlBlack=new JPanel(new FlowLayout());
	JPanel pnlWhiteWins=new JPanel(new FlowLayout());
	JPanel pnlBlackWins=new JPanel(new FlowLayout());
	JPanel pnlDisplay=new JPanel();	
	JPanel [][] dotsArray = new JPanel[8][8];
	JPanel pnlBigDisplay=new JPanel();
	JPanel pnlPass=new JPanel(new FlowLayout());
	JPanel[] pnlList={pnlPlayer,pnlGameControls,pnlMove,pnlWhite,pnlBlack,pnlDisplay,pnlBigDisplay
			,pnlWhiteWins,pnlBlackWins,pnlPass};
	
	public gui(){
		JFrame frame=new JFrame("Othello");
		frame.setLayout(new BorderLayout());
		frame.setBounds(50,50,1900,1280);
		//Display
		pnlBigDisplay.setLayout(new BorderLayout());
		pnlDisplay.setLayout(new GridLayout(8,8));
		Border border = LineBorder.createGrayLineBorder();
		lblCurrentPlayerName.setText(String.valueOf(player));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				final JPanel dots=new JPanel();
				final int checkRow=i;
				final int checkColumn=j;
				dots.setBorder(border);
				dots.setBackground(new Color(0,255,10));
				
					dots.addMouseListener(new MouseListener(){
						public void mouseClicked(MouseEvent e) {
							if(board.getBoard()[checkRow][checkColumn]==seeds.EMPTY){
								if(board.legal(checkRow,checkColumn,player) ){
									board.getBoard()[checkRow][checkColumn]=player;
									lblWhitePieces.setText(String.valueOf(board.getSeeds(playerWhite)));
									lblBlackPieces.setText(String.valueOf(board.getSeeds(playerBlack)));
									displayBoard();
									swapSeeds(player);
								}
						}
						}
						@Override
						public void mouseEntered(MouseEvent arg0) {
							// TODO Auto-generated method stub
						}
						@Override
						public void mouseExited(MouseEvent arg0) {
							// TODO Auto-generated method stub
		
						}
		
						@Override
						public void mousePressed(MouseEvent arg0) {
							// TODO Auto-generated method stub
		
						}
		
						@Override
						public void mouseReleased(MouseEvent arg0) {
							// TODO Auto-generated method stub
						}
					});
				dotsArray[i][j] = dots;
			pnlDisplay.add(dots);
		}
	}
	pnlBigDisplay.setBorder(new TitledBorder("Display"));
	pnlBigDisplay.add(pnlDisplay,BorderLayout.CENTER);

//Change Font and Panel Attributes
	for(JLabel i : lblList){
		changeFont(i);
	}
	for(JPanel i : pnlList){
		changePnl(i);
	}

//Player Panel
	pnlPlayer.setLayout(new FlowLayout());
	pnlPlayer.add(lblCurrentPlayer);
	pnlPlayer.add(lblCurrentPlayerName);

//Game Control Panel
	btnNewGame.setBackground(Color.black);
	btnNewGame.setText("New Game");
	pnlGameControls.setBorder(new TitledBorder("Game Controls"));
	pnlGameControls.setLayout(new GridLayout(3,1));
	pnlWhiteWins.setLayout(new GridLayout(2,1));
	pnlBlackWins.setLayout(new GridLayout(2,1));
	pnlGameControls.setBackground(Color.black);
	pnlGameControls.add(btnNewGame);
	lblWhiteWin.setFont(new Font("Serif", Font.BOLD, 20));
	lblWhiteWins.setFont(new Font("Serif", Font.BOLD, 20));
	lblBlackWin.setFont(new Font("Serif", Font.BOLD, 20));
	lblBlackWins.setFont(new Font("Serif", Font.BOLD, 20));
	pnlWhiteWins.add(lblWhiteWin);
	pnlWhiteWins.add(lblWhiteWins);
	pnlBlackWins.add(lblBlackWin);
	pnlBlackWins.add(lblBlackWins);
	pnlGameControls.add(pnlWhiteWins);
	pnlGameControls.add(pnlBlackWins);
	pnlBigDisplay.add(pnlGameControls,BorderLayout.EAST);

//Display Pieces Panel
	btnPass.setText("Pass ");
	pnlWhite.add(lblWhite);
	pnlWhite.add(lblWhitePieces);
	pnlBlack.add(lblBlack);
	pnlBlack.add(lblBlackPieces);
	pnlPass.add(btnPass);

	pnlMove.setLayout(new FlowLayout());
	pnlMove.add(pnlWhite);
	pnlMove.add(pnlPass);
	pnlMove.add(pnlBlack);
	pnlMove.setBackground(Color.black);
	pnlDisplay.add(pnlMove,BorderLayout.SOUTH);;

//Action Listeners
	btnNewGame.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			board = new gameBoard();
			player=playerBlack;
			for (int r = 0; r < 8; r++) {
				for (int c = 0; c < 8; c++) {
					dotsArray[r][c].removeAll();
				}
			}
			displayBoard();			
		}
	});
	btnPass.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			swapSeeds(player);	
		}
	});
	
	frame.add(pnlPlayer,BorderLayout.NORTH);
	frame.add(pnlGameControls,BorderLayout.EAST);
	frame.add(pnlBigDisplay,BorderLayout.CENTER);
	frame.add(pnlMove,BorderLayout.SOUTH);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
}
	private void displayBoard(){
		gameStatus=0;
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (board.getBoard()[r][c] == seeds.BLACK) {
					ImageIcon picture1 = createImageIcon("black.png");
			        JLabel lblPic1 = new JLabel(picture1);
			        dotsArray[r][c].removeAll();
			        dotsArray[r][c].add(lblPic1);
					pnlDisplay.updateUI();
				} else if (board.getBoard()[r][c] == seeds.WHITE) {
					ImageIcon picture1 = createImageIcon("white.png");
			        JLabel lblPic1 = new JLabel(picture1);
			        dotsArray[r][c].removeAll();
					dotsArray[r][c].add(lblPic1);
					pnlDisplay.updateUI();
				} else {
					dotsArray[r][c].setBackground(new Color(0,255,10));
					gameStatus++;
				}
			}
		}
		if(gameStatus==0){
			GUIConsole.display("Game End");
			lblWhitePieces.setText("");
			lblBlackPieces.setText("");
			if(board.getSeeds(playerWhite)>board.getSeeds(playerBlack)){
				whiteWins++;
				lblWhiteWins.setText(String.valueOf(whiteWins));
				lblBlackWins.setText(String.valueOf(blackWins));
				GUIConsole.display("White Wins");
			}
			else if(board.getSeeds(playerWhite)==board.getSeeds(playerBlack)){
				lblWhiteWins.setText(String.valueOf(whiteWins));
				lblBlackWins.setText(String.valueOf(blackWins));
				GUIConsole.display("Draw");
			}
			else{
				blackWins++;
				lblWhiteWins.setText(String.valueOf(whiteWins));
				lblBlackWins.setText(String.valueOf(blackWins));
				GUIConsole.display("Black Wins");
			}
		}
	}
	public void swapSeeds(seeds currentPlayer){
		if(currentPlayer==seeds.BLACK){
			player=playerWhite;
		}
		else{
			player=playerBlack;
		}
		lblCurrentPlayerName.setText(String.valueOf(player));
	}
	private ImageIcon createImageIcon(String path) {
	       java.net.URL imgURL = getClass().getResource(path);
	       if (imgURL != null) {
	           return new ImageIcon(imgURL);
	       } else {
	           System.out.println("Couldn't find the file: " + path);
	           return null;
	       }
	   }
	public void changeFont(JLabel lbl){
		lbl.setForeground(Color.white);
		lbl.setFont(new Font("Serif", Font.BOLD, 40));
	}
	public void changePnl(JPanel pnl){
		pnl.setBackground(Color.black);
	}
}
