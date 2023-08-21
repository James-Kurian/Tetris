import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;

public class printBoard extends PApplet{


    private int boxSize = 30;
    private int numBoxX = 10;
    private int numBoxY = 22;
    private boolean running = true;
    private String[] moves;
    private int index = 0;
    private int screenX = boxSize*numBoxX;
    private int screenY = boxSize*numBoxY;
    private int timeFrame = 0;
    
    private int time = second();
    
    
    private boolean[][] grid = new boolean[screenX/boxSize][screenY/boxSize];
    private Piece currentPiece;
    ArrayList<String[]> structures = new ArrayList<>();

    public static void main(String[] args) {
        String[] appletArgs = new String[] { "printBoard" };
		PApplet.main(appletArgs);
    }
    public void settings() {
		size(screenX, screenY);
        //the number at the end is the y offset. a horizontal long piece will not have the same y value as a vertical long piece. 
        String[] IPiece = {"oooo0","..o...o...o...o.2","oooo0",".o...o...o...o..2"};
        String[] TPiece = {".o..ooo.0",".o...oo..o..0","ooo..o..-1",".o..oo...o..0"};
        String[] OPiece = {".oo..oo.0",".oo..oo.0",".oo..oo.0",".oo..oo.0"};
        String[] JPiece = {"o...ooo.0",".oo..o...o..0","ooo...o.-1",".o...o..oo..0"};
        String[] LPiece = {"..o.ooo.0",".o...o...oo.0","ooo.o...-1","oo...o...o..0"};
        String[] ZPiece = {"oo...oo.0","..o..oo..o..0","oo...oo.0",".o..oo..o...0"};
        String[] SPiece = {".oo.oo..0",".o...oo...o.0",".oo.oo..0","o...oo...o..0"};

        structures.add(IPiece);
        structures.add(TPiece);
        structures.add(OPiece);
        structures.add(JPiece);
        structures.add(LPiece);
        structures.add(ZPiece);
        structures.add(SPiece);
        
        String str = "";
        
	}

	public void draw(){
        background(255);
        fill(100);
        stroke(0);

        drawGrid();
        
	}


    private void drawGrid() {
        for (int i = 0; i <grid.length; i++){
            line(i*boxSize, 0, i*boxSize, screenY);
            
        }
        for (int i = 0; i <grid[0].length; i++){
            line(0, i*boxSize, screenX, i*boxSize);
        }


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]){
                    square(i*boxSize, j*boxSize, boxSize);
                }
                
            }
            
        }
    }
}