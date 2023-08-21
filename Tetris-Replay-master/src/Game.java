import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;

public class Game extends PApplet{
    private int boxSize = 30;
    private int numBoxX = 10;
    private int numBoxY = 24;
    private boolean running = true;
    private String[] moves;
    private int index = 0;
    private int screenX = boxSize*numBoxX;
    private int screenY = boxSize*numBoxY;
    private int timeFrame = 0;
    private int delay = 0;

    
    
    
    
    private boolean[][] grid = new boolean[screenX/boxSize][screenY/boxSize];
    
    private Piece currentPiece;
    ArrayList<String[]> structures = new ArrayList<>();

    // public Game(boolean displayGame){
    //     this.displayGame=displayGame;
    // }
    public static void main(String[] args) {
        String[] appletArgs = new String[] { "Game" };
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
        
        //Scanner sc = new Scanner(System.in);
        //System.out.println("Enter replay array:");

        // String str = sc.nextLine();
        // sc.close();
        String str = """
                
            [6, left, left, left, space, 2, left, space, 4, rotate, rotate, left, left, left, space, 0, right, right, space, 1, right, right, space, 1, space, 1, rotate, rotate, right, right, right, right, space, 6, left, space, 5, rotate, left, left, left, space, 5, left, space, 0, right, right, space, 6, rotate, left, space, 0, right, right, space, 3, rotate, left, left, left, left, space, 2, left, left, left, left, space, 1, rotate, rotate, rotate, left, left, space, 4, rotate, right, space, 1, right, right, right, right, space, 1, rotate, left, left, left, left, space, 3, rotate, rotate, left, left, space, 1, rotate, rotate, right, right, right, space, 0, rotate, right, right, right, right, space, 3, right, space, 3, rotate, rotate, right, right, right, right, space, 3, rotate, rotate, right, right, right, right, space, 5, right, space, 2, left, left, left, left, space, 6, left, space, 3, rotate, rotate, left, left, left, space, 2, left, space, 3, left, left, left, space, 0, right, right, right, space, 3, left, left, space, 5, rotate, right, right, space, 3, rotate, rotate, left, space, 5, rotate, right, space, 3, rotate, right, space, 4, rotate, rotate, rotate, right, right, right, right, space, 4, rotate, rotate, right, right, right, right, space, 0, rotate, right, right, right, right, space, 3, rotate, rotate, space, 3, rotate, rotate, right, right, right, space, 1, rotate, left, left, left, left, space, 5, right, right, space, 1, rotate, rotate, left, left, space, 1, rotate, rotate, rotate, right, right, right, right, right, space, 0, left, left, left, space, 6, right, right, right, right, space, 4, rotate, rotate, right, space, 3, rotate, rotate, right, right, space, 3, left, left, left, space, 1, right, right, space, 2, left, space, 3, rotate, rotate, rotate, right, right, right, right, right, space, 3, rotate, right, right, right, right, space, 4, rotate, left, left, left, space, 4, left, space, 5, left, left, space, 2, left, space, 4, rotate, rotate, right, right, space, 0, rotate, left, left, left, left, left, space, 4, rotate, rotate, right, right, right, right, space, 5, left, left, left, space, 5, left, left, space, 3, rotate, right, right, space, 2, right, right, right, right, space, 4, rotate, rotate, rotate, right, space, 1, space, 6, right, right, right, space, 4, rotate, rotate, left, left, left, space, 6, rotate, right, space, 2, right, right, right, space, 4, rotate, rotate, right, right, right, space, 5, rotate, left, space, 3, left, left, left, space, 2, left, left, left, space, 0, rotate, right, right, right, right, space, 4, rotate, rotate, right, right, space, 6, space, 0, left, left, left, space, 6, rotate, right, right, right, space, 0, left, left, left, space, 2, space, 2, left, left, left, left, space, 2, left, left, space, 3, rotate, right, right, space, 2, right, right, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 3, right, space, 4, rotate, rotate, right, right, right, right, space, 4, rotate, rotate, left, left, left, space, 1, rotate, rotate, left, space, 2, right, space, 6, left, left, left, space, 2, right, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 0, rotate, left, left, space, 3, right, space, 4, rotate, rotate, left, left, left, space, 6, right, right, right, space, 3, left, left, left, space, 5, rotate, right, space, 1, rotate, rotate, space, 4, rotate, rotate, left, space, 4, rotate, rotate, rotate, left, left, left, space, 6, rotate, right, right, right, right, space, 6, rotate, right, right, space, 6, rotate, space, 5, left, left, space, 0, rotate, left, left, left, left, left, space, 2, left, left, left, space, 0, rotate, left, left, space, 5, left, left, left, space, 3, rotate, rotate, right, right, right, right, space, 2, right, space, 4, rotate, rotate, right, space, 1, right, right, right, right, space, 3, rotate, rotate, right, right, right, right, space, 1, rotate, rotate, right, right, right, space, 0, right, right, right, space, 5, space, 2, left, left, left, left, space, 5, rotate, left, left, space, 4, rotate, rotate, right, right, space, 4, rotate, rotate, right, space, 0, space, 5, right, right, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 4, rotate, rotate, rotate, left, left, space, 1, rotate, rotate, right, right, right, space, 0, rotate, left, left, left, left, left, space, 4, space, 5, left, left, left, space, 1, right, right, right, space, 3, rotate, rotate, left, left, left, space, 1, rotate, left, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 0, left, left, left, space, 5, right, space, 6, rotate, right, space, 4, right, right, right, right, space, 6, right, right, right, right, space, 5, right, right, space, 6, rotate, left, space, 6, left, left, left, space, 3, rotate, rotate, left, space, 0, rotate, left, left, left, left, left, space, 5, rotate, left, left, left, space, 4, right, right, right, right, space, 5, right, space, 1, rotate, rotate, left, left, left, space, 5, right, right, right, space, 2, right, right, right, right, space, 6, right, right, right, space, 3, space, 0, left, left, left, space, 5, right, space, 3, rotate, rotate, right, right, right, right, space, 0, left, left, left, space, 5, rotate, right, right, space, 4, rotate, rotate, rotate, space, 4, rotate, rotate, rotate, right, space, 4, rotate, right, right, right, right, space, 3, left, left, left, space, 6, left, space, 1, rotate, rotate, left, left, left, space, 4, rotate, rotate, rotate, right, right, space, 5, rotate, right, right, right, space, 2, left, left, left, left, space, 0, rotate, right, right, right, right, space, 6, rotate, left, left, space, 4, right, space, 1, rotate, right, right, right, space, 4, space, 1, rotate, rotate, rotate, right, right, right, right, right, space, 4, rotate, rotate, left, space, 5, rotate, right, right, space, 0, left, space, 3, rotate, rotate, rotate, left, left, left, space, 6, right, right, space, 0, rotate, left, left, left, left, left, space, 1, rotate, right, right, right, right, space, 5, left, left, space, 1, rotate, rotate, space, 5, left, left, left, space, 1, rotate, rotate, rotate, right, right, right, right, right, space, 4, rotate, rotate, right, space, 3, rotate, rotate, right, right, space, 6, rotate, left, left, space, 1, rotate, rotate, space, 6, rotate, right, right, right, space, 4, rotate, rotate, rotate, left, left, left, space, 0, left, space, 6, rotate, right, right, right, right, space, 1, rotate, rotate, right, right, space, 5, rotate, left, left, left, left, space, 1, left, space, 2, space, 3, rotate, rotate, right, right, right, right, space, 2, right, right, right, space, 6, rotate, left, left, left, space, 6, rotate, left, left, space, 3, rotate, left, left, left, left, space, 0, rotate, right, space, 3, rotate, rotate, left, left, space, 0, rotate, right, right, right, right, space, 6, right, space, 5, right, right, right, space, 0, rotate, left, space, 6, rotate, right, right, right, space, 5, rotate, right, space, 6, rotate, right, right, right, right, space, 2, left, left, space, 5, rotate, left, left, left, left, space, 4, rotate, rotate, right, right, space, 1, left, space, 1, rotate, left, left, left, left, space, 4, right, space, 5, left, left, left, space, 0, left, space, 1, right, right, right, right, space, 0, left, left, left, space, 1, rotate, rotate, right, right, right, space, 2, space, 5, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 4, left, left, space, 1, rotate, left, left, left, left, space, 0, space, 4, rotate, right, right, right, space, 6, rotate, left, left, left, space, 5, left, left, space, 0, rotate, left, left, left, left, left, space, 0, rotate, right, right, right, right, space, 0, rotate, right, right, right, space, 0, rotate, left, space, 0, rotate, space, 3, rotate, rotate, left, left, space, 5, rotate, right, right, space, 6, space, 6, left, left, space, 5, rotate, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 4, rotate, rotate, left, left, space, 0, rotate, left, left, left, left, left, space, 3, rotate, rotate, right, space, 5, right, right, right, right, space, 6, rotate, right, right, right, right, space, 2, right, right, space, 1, rotate, rotate, space, 2, left, left, left, space, 4, space, 0, left, left, space, 6, rotate, right, right, right, right, space, 0, rotate, left, left, left, left, left, space, 5, right, right, space, 4, rotate, rotate, left, space, 5, rotate, left, left, left, space, 4, space, 5, left, space, 0, right, right, right, space, 1, rotate, rotate, right, right, right, space, 2, left, left, left, left, space, 0, right, space, 3, rotate, rotate, right, right, right, space, 2, left, left, left, left, space, 2, left, left, space, 0, rotate, right, right, right, right, space, 2, space, 2, right, right, space, 0, left, space, 3, rotate, rotate, rotate, right, right, right, right, right, space, 4, rotate, rotate, left, left, space, 3, rotate, rotate, right, right, right, space, 0, rotate, left, left, left, left, left, space, 2, space, 1, left, left, space, 0, right, right, right, space, 0, space, 6, rotate, left, left, left, left, space, 0, left, space, 2, right, right, right, space, 6, rotate, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 2, left, space, 4, rotate, rotate, right, right, right, right, space, 5, left, left, left, space, 0, left, left, left, space, 2, space, 4, rotate, rotate, right, right, right, space, 4, left, left, space, 3, rotate, rotate, right, space, 6, rotate, right, right, right, right, space, 6, left, left, space, 4, rotate, rotate, rotate, right, right, right, space, 3, rotate, left, left, left, left, space, 3, rotate, rotate, left, space, 0, rotate, space, 3, rotate, rotate, right, right, right, space, 1, left, space, 4, rotate, left, left, left, left, space, 6, left, left, space, 5, rotate, space, 3, right, right, right, space, 4, rotate, left, left, left, left, space, 2, left, left, left, space, 0, rotate, right, right, right, right, space, 0, right, right, right, space, 2, left, space, 2, right, right, right, space, 1, rotate, rotate, right, right, space, 5, right, right, right, right, space, 5, rotate, right, right, space, 0, rotate, right, right, right, right, space, 6, rotate, left, left, left, left, space, 1, rotate, left, left, space, 4, rotate, space, 0, rotate, left, left, space, 4, rotate, rotate, rotate, right, space, 1, rotate, rotate, left, left, left, space, 5, rotate, right, right, right, right, space, 2, left, left, left, space, 5, rotate, right, right, space, 3, right, space, 2, left, left, space, 0, rotate, left, left, left, left, left, space, 5, rotate, right, right, right, right, space, 3, rotate, left, left, left, space, 6, right, right, right, right, space, 5, right, space, 1, space, 2, right, right, space, 4, rotate, rotate, left, left, left, space, 3, rotate, rotate, rotate, right, right, right, right, right, space, 2, right, right, right, space, 5, right, space, 1, rotate, rotate, rotate, left, space, 3, rotate, rotate, right, space, 0, space, 0, space, 5, rotate, left, left, left, space, 2, right, right, right, space, 6, rotate, right, right, space, 2, space, 3, rotate, left, left, left, left, space, 6, left, left, space, 2, left, left, space, 3, rotate, left, left, left, left, space, 2, left, left, left, left, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 2, space, 5, left, left, space, 6, rotate, right, right, space, 5, left, space, 3, rotate, right, right, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 6, rotate, right, right, space, 1, rotate, rotate, rotate, right, space, 4, rotate, rotate, left, left, space, 2, right, right, right, right, space, 3, rotate, rotate, left, space, 3, rotate, left, left, left, left, space, 3, rotate, rotate, space, 4, rotate, rotate, right, right, right, right, space, 6, left, left, space, 2, space, 2, left, left, space, 5, rotate, left, left, left, left, space, 0, rotate, right, space, 3, right, right, right, right, space, 4, rotate, right, right, right, right, space, 3, rotate, right, right, right, space, 2, space, 6, left, space, 1, rotate, left, left, left, left, space, 1, rotate, rotate, left, left, left, space, 5, rotate, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 0, rotate, right, space, 1, rotate, rotate, rotate, right, space, 1, rotate, rotate, rotate, right, right, right, right, right, space, 3, left, left, space, 0, rotate, left, left, left, left, left, space, 0, rotate, right, right, space, 3, left, space, 6, rotate, left, left, left, left, space, 1, rotate, right, right, right, right, space, 0, space, 2, left, left, space, 0, left, left, left, space, 2, space, 1, rotate, right, right, space, 5, space, 2, right, right, right, right, space, 1, right, right, right, right, space, 2, right, space, 3, rotate, left, left, left, space, 5, left, space, 4, rotate, rotate, rotate, right, right, right, space, 2, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 0, rotate, left, left, left, left, left, space, 4, rotate, rotate, left, left, space, 3, rotate, rotate, right, right, right, space, 1, left, left, left, space, 0, space, 5, right, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 0, left, space, 1, rotate, left, left, left, left, space, 4, left, space, 3, right, right, space, 4, left, left, space, 4, left, left, left, space, 3, rotate, rotate, right, space, 6, rotate, right, right, right, right, space, 5, rotate, right, right, right, space, 0, rotate, right, right, right, right, space, 6, right, right, right, space, 5, left, space, 1, rotate, rotate, right, space, 6, rotate, left, left, left, left, space, 3, left, left, space, 0, right, space, 0, left, space, 3, rotate, rotate, rotate, right, right, right, right, right, space, 1, right, right, right, space, 0, rotate, left, left, left, left, left, space, 5, rotate, left, left, space, 6, right, right, space, 4, rotate, rotate, rotate, space, 1, rotate, right, right, right, right, space, 5, rotate, left, left, left, space, 6, left, left, left, space, 0, rotate, space, 4, rotate, right, right, space, 2, left, space, 6, rotate, right, right, right, right, space, 1, rotate, left, left, left, left, space, 5, rotate, left, left, space, 3, rotate, right, right, right, space, 6, rotate, left, left, left, left, space, 2, space, 6, rotate, left, left, left, space, 4, rotate, rotate, right, right, right, space, 1, space, 4, right, right, right, space, 6, left, space, 2, right, space, 6, left, left, space, 6, rotate, left, space, 2, right, space, 5, rotate, left, left, left, space, 0, rotate, right, right, right, right, space, 4, rotate, rotate, right, right, right, right, space, 5, space, 3, right, right, right, right, space, 3, rotate, rotate, right, right, right, space, 2, right, space, 4, rotate, rotate, left, space, 3, rotate, left, left, left, left, space, 6, rotate, left, left, left, left, space, 6, rotate, left, left, left, left, space, 2, left, left, space, 5, left, left, left, space, 1, rotate, rotate, right, right, right, right, space, 5, rotate, right, right, right, space, 5, rotate, right, right, space, 6, rotate, left, left, space, 1, rotate, space, 4, rotate, right, space, 2, right, right, space, 3, rotate, space, 2, right, right, right, right, space, 1, rotate, rotate, rotate, left, space, 0, rotate, right, right, right, right, space, 6, rotate, right, right, right, right, space, 2, left, left, left, left, space, 5, right, right, space, 0, space, 3, rotate, rotate, right, right, right, right, space, 6, left, left, left, space, 1, right, right, right, right, space, 6, right, right, right, space, 6, right, space, 5, rotate, left, left, left, left, space, 0, rotate, left, left, left, space, 6, space, 5, rotate, left, left, left, left, space, 5, right, right, space, 0, rotate, right, right, right, right, space, 0, rotate, left, left, space, 6, rotate, right, right, right, space, 1, rotate, rotate, rotate, left, left, space, 0, rotate, left, space, 5, right, right, right, right, space, 3, rotate, left, left, left, left, space, 0, rotate, space, 0, rotate, right, space, 2, right, right, right, space, 0, left, left, space, 4, rotate, rotate, left, left, left, space, 4, space, 2, left, left, left, left, space, 1, left, space, 6, rotate, left, left, left, space, 3, rotate, left, left, left, left, space, 3, rotate, rotate, right, space, 6, rotate, left, left, space, 2, space, 5, left, space, 3, rotate, rotate, right, right, right, right, space, 6, rotate, right, right, space, 3, right, right, right, right, space, 0, rotate, right, right, right, space, 6, right, right, space, 1, right, right, right, space, 5, space, 6, left, left, left, space, 2, left, left, left, space, 5, rotate, right, space, 4, rotate, left, space, 5, rotate, right, space, 5, rotate, space, 1, rotate, left, left, space, 1, rotate, rotate, rotate, right, right, right, right, space, 3, rotate, left, left, left, left, space, 2, left, left, left, left, space, 5, rotate, right, right, right, space, 6, space, 3, left, left, left, space, 4, rotate, right, right, space, 6, right, space, 6, rotate, left, left, space, 3, rotate, rotate, right, right, right, right, space, 6, rotate, left, left, left, left, space, 3, rotate, rotate, right, right, right, right, space, 1, rotate, space, 0, rotate, right, right, right, right, space, 2, left, left, left, space, 0, rotate, right, right, right, right, space, 0, rotate, left, left, space, 2, right, right, right, space, 2, left, left, left, space, 0, rotate, right, right, right, right, space, 1, rotate, rotate, right, right, space, 6, rotate, left, space, 4, rotate, rotate, rotate, right, space, 2, right, right, space, 4, rotate, right, right, right, right, space, 1, right, right, right, space, 5, rotate, left, left, space, 3, right, space, 4, right, right, space, 4, rotate, rotate, left, space, 5, right, space, 2, left, left, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 3, rotate, left, left, left, left, space, 2, left, left, left, left, space, 2, left, left, left, left, space, 5, right, right, space, 1, rotate, right, right, right, right, space, 6, left, left, left, space, 6, rotate, left, space, 0, rotate, right, right, right, right, space, 1, rotate, left, left, left, left, space, 2, right, right, right, space, 5, right, space, 4, rotate, rotate, left, space, 5, right, right, space, 6, rotate, right, right, right, right, space, 3, rotate, rotate, right, right, space, 0, left, left, space, 4, rotate, rotate, left, left, left, space, 4, space, 3, rotate, rotate, right, right, right, space, 2, left, left, left, left, space, 5, right, right, space, 4, left, space, 2, left, left, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 4, right, right, right, right, space, 6, rotate, left, left, left, left, space, 3, right, space, 5, right, right, right, space, 4, left, left, space, 6, rotate, right, right, right, right, space, 4, rotate, rotate, right, right, space, 2, left, left, left, space, 4, rotate, rotate, right, space, 4, rotate, rotate, space, 4, rotate, rotate, left, space, 4, rotate, right, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 3, rotate, left, left, left, left, space, 5, right, right, right, right, space, 2, left, left, left, left, space, 0, rotate, right, space, 2, left, left, space, 5, right, right, right, space, 1, rotate, rotate, rotate, right, space, 2, left, left, left, left, space, 2, left, left, space, 6, left, left, left, space, 0, rotate, left, space, 0, rotate, right, right, right, right, space, 4, rotate, rotate, right, right, space, 1, rotate, rotate, left, space, 1, rotate, left, left, left, left, space, 5, left, left, left, space, 1, rotate, rotate, right, right, right, right, space, 3, rotate, rotate, rotate, right, right, space, 5, left, space, 6, rotate, right, right, right, space, 5, right, right, right, right, space, 6, rotate, space, 2, left, left, left, left, space, 5, rotate, right, right, space, 3, rotate, rotate, rotate, left, space, 0, rotate, right, right, right, right, space, 4, rotate, rotate, rotate, right, space, 5, space, 5, rotate, right, right, space, 0, rotate, right, right, right, space, 2, right, space, 1, rotate, rotate, rotate, left, left, space, 6, space, 2, right, right, right, space, 2, space, 5, rotate, left, left, space, 3, rotate, left, left, left, left, space, 6, rotate, left, left, left, left, space, 6, left, left, space, 6, left, left, left, space, 4, rotate, rotate, right, right, right, space, 0, rotate, right, right, right, right, space, 5, right, right, space, 2, left, space, 2, left, left, left, space, 3, rotate, rotate, space, 5, right, right, right, right, space, 0, rotate, right, right, right, right, space, 3, right, right, right, space, 4, rotate, right, right, right, space, 1, right, space, 3, left, left, space, 2, right, right, right, right, space, 4, left, space, 3, left, left, space, 6, rotate, right, space, 6, space, 6, rotate, right, right, space, 0, rotate, left, left, left, left, left, space, 1, rotate, rotate, left, left, space, 2, right, right, right, right, space, 2, right, right, right, space, 3, right, space, 4, left, left, space, 6, left, left, space, 0, rotate, left, left, left, left, left, space, 0, rotate, left, left, left, left, left, space, 5, right, space, 3, rotate, rotate, right, right, right, right, space, 0, left, space, 6, rotate, left, left, left, left, space, 4, right, right, right, space, 0, rotate, right, right, right, right, space, 1, left, left, space, 2, right, right, space, 4, rotate, rotate, rotate, space, 1, rotate, rotate, right, right, right, right, space, 2, left, left, left, left, space, 6, right, right, space, 5, left, left, space, 6, rotate, space, 5, right, right, right, right, space, 6, right, right, space, 5, rotate, left, space, 1, rotate, right, space, 1, rotate, left, left, space, 2, left, space, 0, rotate, left, left, left, left, left, space, 4, rotate, rotate, rotate, left, left, left, space, 2, left, left, left, left, space, 6, rotate, right, right, right, right, space, 4, rotate, rotate, left, space, 6, rotate, right, space, 4, rotate, rotate, right, right, right, right, space, 6, rotate, right, right, right, right, space, 2, left, left, left, left, space, 5, right, right, space, 5, right, right, right, space, 5, left, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 1, left, left, left, space, 6, right, space, 0, rotate, left, left, left, left, left, space, 3, right, right, right, right, space, 6, space, 1, rotate, left, left, space, 3, rotate, rotate, right, space, 1, rotate, left, left, left, space, 2, right, right, right, right, space, 2, left, left, left, left, space, 2, left, left, left, left, space, 2, left, left, left, left, space, 0, rotate, right, right, space, 3, rotate, left, space, 1, left, space, 4, rotate, right, space, 4, rotate, right, right, right, right, space, 0, rotate, left, left, left, space, 0, rotate, left, left, space, 0, rotate, left, space, 2, right, right, space, 0, rotate, space, 0, right, right, right, space, 2, right, right, space, 6, rotate, right, right, right, right, space, 5, rotate, left, left, space, 5, rotate, space, 3, rotate, rotate, right, right, right, space, 6, left, left, space, 0, rotate, right, right, right, right, space, 1, rotate, right, right, space, 3, rotate, left, left, left, left, space, 4, rotate, rotate, rotate, space, 6, rotate, right, right, right, space, 1, rotate, right, space, 2, left, left, left, left, space, 1, rotate, left, left, space, 5, right, right, right, right, space, 0, rotate, right, right, right, right, space, 0, right, right, space, 3, rotate, space, 6, left, left, left, space, 1, right, right, right, space, 6, right, right, space, 3, rotate, left, space, 0, rotate, left, left, left, left, left, space, 0, rotate, left, left, left, left, space, 4, rotate, rotate, left, space, 2, left, left, space, 4, rotate, right, right, right, right, space, 1, rotate, rotate, right, space, 1, rotate, rotate, right, right, right, space, 3, rotate, rotate, left, space, 1, rotate, left, left, left, left, space, 1, rotate, right, space, 2, right, right, right, space, 1, rotate, rotate, right, right, space, 1, left, left, space, 2, left, left, left, left, space, 4, rotate, rotate, rotate, space, 4, rotate, rotate, left, space, 4, right, right, space, 5, right, space, 5, right, right, space, 0, rotate, right, right, right, right, space, 5, rotate, left, space, 1, rotate, right, right, right, right, space, 1, rotate, left, left, left, space, 2, right, space, 6, rotate, right, right, right, space, 3, rotate, left, left, left, left, space, 4, rotate, rotate, rotate, left, left, space, 0, rotate, right, right, right, right, space, 0, rotate, right, right, right, right, space, 5, rotate, left, space, 4, rotate, rotate, rotate, right, right, right, right, space, 2, right, space, 6, left, space, 3, rotate, left, left, left, left, space, 1, rotate, rotate, rotate, right, right, right, right, right, space, 5, rotate, right, right, right, space, 5, rotate, right, right, space, 6, left, left, space, 2, space, 4, rotate, rotate, left, left, space, 3, rotate, left, left, left, left, space, 3, rotate, rotate, left, left, left, space, 5, space, 5, rotate, right, right, space, 0, left, left, left, space, 6, rotate, right, right, right, right, space, 1, rotate, rotate, right, space, 4, rotate, right, right, right, right, space, 5, left, left, left, space, 6, rotate, left, left, space, 6, left, left, left, space, 0, right, space, 5, space, 4, rotate, rotate, left, left, left, space, 3, rotate, rotate, space, 1, space, 6, right, right, right, space, 5, rotate, right, right, space, 1, rotate, rotate, right, right, space, 2, right, right, right, right, space, 6, left, space, 0, rotate, right, right, right, right, space, 2, right, right, right, right, space, 5, rotate, left, left, left, left, space, 2, right, space, 5, rotate, right, right, right, space, 4, rotate, rotate, left, space, 4, rotate, rotate, left, left, space, 1, right, space, 2, left, space, 0, rotate, left, left, left, left, left, space, 1, rotate, rotate, right, right, right, space, 4, right, right, space, 3, rotate, rotate, rotate, left, left, space, 3, rotate, rotate, rotate, left, left, left, space, 3, rotate, left, left, left, left, space, 5, left, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 2, right, space, 5, right, right, right, right, space, 3, rotate, rotate, left, space, 6, rotate, space, 2, left, left, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 5, rotate, right, right, right, space, 0, rotate, right, space, 2, left, left, left, left, space, 2, left, left, left, left, space, 1, rotate, right, right, right, space, 2, left, left, space, 6, rotate, space, 6, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 6, rotate, right, right, right, right, space, 5, rotate, left, space, 3, left, left, left, space, 5, rotate, right, space, 3, left, left, space, 1, rotate, right, right, right, space, 6, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 4, rotate, left, left, space, 4, rotate, left, space, 6, right, space, 3, rotate, left, left, left, left, space, 3, rotate, right, right, right, space, 0, right, right, space, 0, right, right, space, 3, rotate, rotate, left, space, 5, rotate, left, left, left, left, space, 5, rotate, left, left, left, left, space, 1, left, space, 5, space, 4, rotate, rotate, left, left, left, space, 0, rotate, right, right, right, right, space, 6, rotate, right, space, 6, space, 0, rotate, right, right, space, 5, rotate, right, right, right, right, space, 5, rotate, right, right, right, right, space, 5, rotate, left, space, 4, rotate, rotate, rotate, left, left, space, 3, rotate, right, right, space, 2, left, left, left, space, 0, rotate, left, left, left, left, left, space, 4, rotate, rotate, right, right, space, 2, left, left, left, space, 3, rotate, right, right, right, right, space, 1, rotate, left, space, 0, rotate, left, left, left, left, left, space, 1, right, right, space, 0, rotate, right, right, right, right, space, 0, rotate, left, left, left, left, space, 5, space, 1, rotate, rotate, rotate, right, right, right, right, space, 1, right, right, space, 5, rotate, left, left, space, 0, rotate, left, space, 6, left, left, space, 1, rotate, right, space, 5, rotate, left, left, left, left, space, 6, left, space, 4, rotate, rotate, left, left, left, space, 4, rotate, rotate, right, right, right, right, space, 4, rotate, right, right, right, right, space, 0, left, left, left, space, 5, right, right, space, 5, right, space, 1, right, right, right, right, space, 4, rotate, rotate, rotate, right, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 6, right, space, 1, left, left, space, 3, right, right, right, right, space, 2, left, space, 0, rotate, left, left, left, left, left, space, 5, rotate, left, left, left, space, 5, right, right, right, right, space, 6, rotate, right, space, 3, rotate, rotate, rotate, space, 6, rotate, right, space, 6, rotate, left, left, left, left, space, 3, rotate, rotate, left, left, space, 0, right, right, right, space, 3, rotate, rotate, left, left, left, space, 0, space, 2, right, right, right, space, 0, rotate, right, right, right, right, space, 2, left, left, left, left, space, 3, rotate, left, left, space, 3, rotate, rotate, right, space, 2, right, right, right, space, 2, space, 6, left, space, 6, left, left, left, space, 2, right, right, right, space, 5, rotate, left, left, left, left, space, 4, rotate, rotate, rotate, right, right, space, 4, left, space, 0, rotate, right, right, right, right, space, 0, right, right, space, 5, left, left, space, 6, rotate, right, right, right, right, space, 3, right, right, space, 5, left, space, 1, rotate, left, left, left, left, space, 5, right, right, space, 3, rotate, rotate, right, right, right, right, space, 6, rotate, left, space, 3, rotate, rotate, rotate, left, left, space, 6, rotate, space, 1, rotate, rotate, left, space, 2, left, left, left, left, space, 0, right, right, right, space, 1, right, right, right, space, 0, rotate, right, right, right, right, space, 4, rotate, right, space, 2, left, left, left, left, space, 5, left, left, space, 1, rotate, rotate, rotate, right, right, right, right, space, 3, rotate, right, right, space, 0, rotate, left, left, left, left, left, space, 3, rotate, rotate, left, left, space, 1, rotate, space, 0, left, left, space, 5, right, right, right, right, space, 6, right, right, space, 1, rotate, rotate, right, space, 1, rotate, rotate, rotate, right, right, right, right, right, space, 3, left, left, space, 1, rotate, right, right, right, space, 6, right, right, space, 3, rotate, left, left, left, left, space, 1, rotate, rotate, left, space, 0, left, space, 1, rotate, right, right, right, right, space, 1, right, right, space, 3, left, left, left, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 6, space, 6, rotate, left, left, space, 5, left, space, 3, rotate, rotate, right, right, space, 6, rotate, left, left, left, left, space, 2, right, right, right, right, space, 0, right, space, 6, rotate, left, left, left, left, space, 0, left, space, 5, rotate, right, right, space, 6, right, right, space, 6, space, 6, left, left, space, 5, rotate, right, right, right, right, space, 1, rotate, rotate, right, right, right, right, space, 1, right, space, 4, rotate, left, left, left, left, space, 1, left, left, space, 4, right, right, right, space, 5, left, space, 5, space, 3, rotate, left, left, left, space, 1, rotate, rotate, right, right, space, 3, rotate, left, left, left, left, space, 1, left, space, 0, rotate, right, right, right, right, space, 6, rotate, right, right, right, space, 6, left, left, space, 4, rotate, right, space, 1, rotate, left, left, left, left, space, 4, rotate, rotate, rotate, space, 5, left, left, left, space, 5, rotate, right, right, space, 2, right, right, right, right, space, 0, left, space, 2, left, left, left, left, space, 1, rotate, right, right, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 0, left, space, 0, left, space, 0, right, right, right, space, 3, right, right, right, right, space, 6, rotate, space, 2, left, left, space, 2, left, left, left, left, space, 1, rotate, right, right, right, right, space, 4, rotate, rotate, rotate, right, right, space, 4, left, space, 0, rotate, right, right, space, 0, rotate, left, left, left, left, left, space, 0, rotate, left, left, left, left, space, 6, rotate, right, right, right, right, space, 1, rotate, right, space, 2, left, left, space, 2, right, right, space, 2, left, left, space, 1, rotate, space, 2, right, right, right, right, space, 3, rotate, rotate, right, right, right, right, space, 6, right, right, space, 3, rotate, left, left, left, left, space, 5, rotate, right, space, 4, left, space, 5, right, right, right, right, space, 2, left, left, left, left, space, 4, rotate, rotate, space, 0, space, 5, left, left, left, space, 6, rotate, left, left, left, space, 4, rotate, rotate, right, right, right, right, space, 5, left, space, 0, rotate, left, left, left, left, left, space, 6, rotate, space, 6, left, space, 4, rotate, rotate, rotate, right, right, right, right, right, space, 0, right, right, right, space, 2, right, right, space, 2, right, right, right, right, space, 4, rotate, rotate, right, right, space, 3, rotate, rotate, right, right, right, space, 1, rotate, rotate, left, left, left, space, 3, left, left, left, space, 1, space, 0, rotate, right, right, right, right, space, 0, right, right, space, 1, rotate, rotate, left, left, space, 1, rotate, rotate, left, left, left, space, 1, space, 0, left, left, left, space, 1, right, right, right, space, 5, right, space, 2, right, right, right, right, space, 0, left, left, left, space, 3, rotate, rotate, rotate, right, right, right, space, 4, right, space, 2, left, left, left, left, space, 3, rotate, rotate, rotate, left, space, 4, rotate, right, right, right, right, space, 2, space, 1, rotate, rotate, rotate, left, left, space, 1, rotate, left, left, left, left, space, 1, space, 3, left, left, space, 6, right, right, right, space, 3, rotate, rotate, space, 4, rotate, rotate, right, right, right, space, 0, rotate, right, right, right, right, space, 4, right, right, right, space, 6, right, right, right, space, 3, space, 3, right, space, 3, right, right, space, 6, rotate, left, left, left, space, 4, left, left, space, 3, rotate, rotate, right, right, right, space, 6, left, left, space, 5, rotate, space, 6, space, 3, right, right, right, space, 4, rotate, rotate, space, 4, rotate, rotate, rotate, right, right, space, 2, right, right, right, space, 5, rotate, left, left, left, space, 4, rotate, rotate, left, space, 2, right, right, right, space, 1, left, space, 5, right, space, 6, rotate, right, right, space, 3, space, 6, rotate, right, right, space, 6, rotate, left, space, 6, rotate, space, 4, rotate, rotate, rotate, right, right, right, space, 4, rotate, rotate, right, right, space, 5, rotate, left, space, 1, right, right, space, 5, rotate, left, space]
                 
                 """;


        str = str.substring(str.indexOf("[")+1,str.lastIndexOf("]"));
        moves = str.split(", ");
        
        currentPiece = new Piece(structures.get(Integer.parseInt(moves[index])),3,2,numBoxX,numBoxY,this);
	}

	public void draw(){
        background(255);
        fill(100);
        stroke(0);
        for (int i = 0; i <grid.length; i++){
            line(i*boxSize, 0, i*boxSize, screenY);
            
        }
        for (int i = 0; i <grid[0].length; i++){
            
            if (i==4){
                stroke(255,0,0);
            }
            line(0, i*boxSize, screenX, i*boxSize);
            stroke(0);
        }

        if ((millis()-timeFrame)>=delay&&running){
            // if (index==5700){
            //     delay = 1000;
            // }

            





            index++;
            System.out.println(index);
            if (index>=moves.length){
                System.out.println("replay finished");
                running=false;
            }
            else if (moves[index].equals("rotate")){
                currentPiece.rotate();
            }else if (moves[index].equals("left")){
                currentPiece.left();
            }else if (moves[index].equals("right")){
                currentPiece.right();
            }else if(moves[index].equals("move")){
                currentPiece.move();
            }else if (moves[index].equals("space")){
                currentPiece.space();
            }else if (moves[index].equals("colDet")){
                collisionDet();
            // }else{
                
            //     currentPiece = new Piece(structures.get(Integer.parseInt(moves[index])),3,0,numBoxX,numBoxY,this);
             }
            timeFrame=millis();
        }
        // if (second()>time){
        //    collisionDet();

        // }else if (time==59&&second()<time){
        //     collisionDet();
        //     time=0;
        // }
        if (running){
            drawPiece(currentPiece);
        }

        drawGrid();


        
		
	}
    public void collisionDet(){
        //time = second();
        Boolean collision = false;
        collision = currentPiece.move();

        if (collision){

            addToGrid(currentPiece);
            //currentPiece = new Piece(structures.get((int)(Math.random()*structures.size())),3,0,numBoxX,numBoxY,this);
            index++;
            if (index<moves.length){
                currentPiece = new Piece(structures.get(Integer.parseInt(moves[index])),3,2,numBoxX,numBoxY,this);
            }
        }
            clearLines();
        }
        

    

    private void clearLines() {
        int index = (grid[0].length)-1;
    
        Boolean changeIndex = false;
        boolean[][] newGrid = new boolean[numBoxX][numBoxY];
        for (int i = grid[0].length-1; i >= 0 ; i--){
            changeIndex = false;
            for (int j = 0; j<grid.length; j++){
                if (!grid[j][i]){
                    changeIndex=true;
                }
                newGrid[j][index]=grid[j][i];
            }
            if (changeIndex){
                index--;
            }

        }
        grid=newGrid;


        // boolean[][] g = grid;
        // for (int i = 0; i <g[0].length; i++){
        //     for (int j = 0; j<g.length; j++){
        //         if (g[j][i]){
        //             System.out.print("o");
        //         }else{
        //             System.out.print(".");
        //         }
        //     }
        //     System.out.println();
        // }
        // System.out.println("__________");
    }

    private void drawGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]){
                    square(i*boxSize, j*boxSize, boxSize);
                }
                
            }
            
        }
    }
    private void addToGrid(Piece p) {
        // String str = p.getStructure();
        // int YOffset = 0;

        // if (str.lastIndexOf(".")>str.lastIndexOf("o")){
        //     YOffset = Integer.parseInt(str.substring(str.lastIndexOf(".")+1));
        // }else{
        //     YOffset = Integer.parseInt(str.substring(str.lastIndexOf("o")+1));

        // }
        // int[] currentPos = {p.getPosX(),p.getPosY()-YOffset};
       

        // for (int i = 0; i <str.length()-1; i++){
        //     if (str.substring(i, i+1).equals("o")){
        //         grid[currentPos[0]][currentPos[1]]=true;
        //     }
        //     currentPos[0]++;
        //     if ((i+1)%4==0){
        //         currentPos[0]=p.getPosX();
        //         currentPos[1]++;
        //     }
            

        // }




        String str = p.getStructure();

        int YOffset = p.getYOffset();
        int[] currentPos = {p.getPosX(),p.getPosY()-YOffset};


        int index = str.indexOf("o");
        while (index!=-1){
            grid[currentPos[0]+index%4][currentPos[1]+index/4]=true;
               
            
            index = str.indexOf("o",index+1);
        
        }

    //     boolean[][] g = grid;
    //     for (int i = 0; i <g[0].length; i++){
    //         for (int j = 0; j<g.length; j++){
    //             if (g[j][i]){
    //                 System.out.print("o");
    //             }else{
    //                 System.out.print(".");
    //             }
    //         }
    //         System.out.println();
    //     }
    //     System.out.println("__________");
    
    }



    public void keyPressed(){
        // if (key==CODED){
        //     if (keyCode==UP){
        //         currentPiece.rotate();
                
        //     }else if(keyCode==LEFT){
        //         currentPiece.left();
                
        //     }
        //     else if (keyCode==RIGHT){
        //         currentPiece.right();

        //     }else if (keyCode==DOWN){
        //         currentPiece.move();

        //     }
        // } else if (key == ' '){
        //     currentPiece.space();
        // }

    }


    public void drawPiece(Piece p){
        String str = p.getStructure();
        int YOffset = 0;

        if (str.lastIndexOf(".")>str.lastIndexOf("o")){
            YOffset = Integer.parseInt(str.substring(str.lastIndexOf(".")+1));
        }else{
            YOffset = Integer.parseInt(str.substring(str.lastIndexOf("o")+1));

        }
        int[] currentPos = {p.getPosX(),p.getPosY()-YOffset};
       

        for (int i = 0; i <str.length()-1; i++){
            if (str.substring(i, i+1).equals("o")){
                square(currentPos[0]*boxSize, currentPos[1]*boxSize, boxSize);
            }
            currentPos[0]++;
            if ((i+1)%4==0){
                currentPos[0]=p.getPosX();
                currentPos[1]++;
            }
            

        }
    }
    public boolean[][] getGrid(){
        return grid;
    }

    

}
