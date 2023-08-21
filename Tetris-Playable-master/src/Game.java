import java.util.ArrayList;

import processing.core.PApplet;
/**
 * I would like to honour the original people of this land, to say thank you for taking such good care of our mother the earth, so that we could all have a place to call home today. Thank you to the Huron Peoples also known as the Wendat Nation, the Haudenosaunee also known as the Five Nation Confederacy, the Three Fire Confederacy of the Anishnaabe, which includes The Mississauga of the New Credit. These are the original peoples of this land and we as settlers and newcomers have a responsibility to honour these people by acknowledging them and taking good care of this land and each other
 */

public class Game extends PApplet{
    private int boxSize = 30;
    private int numBoxX = 10;
    private int numBoxY = 22;
    


    private int screenX = boxSize*numBoxX;
    private int screenY = boxSize*numBoxY;
    private boolean displayGame;
    
    private int time = second();
    
    
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
        
        
        currentPiece = new Piece(structures.get((int)(Math.random()*structures.size())),3,2,numBoxX,numBoxY,this);
	}

	public void draw(){
        //currentPiece.setX(currentPiece.startOfPiece(currentPiece.getStructure())*-1);
        background(255);
        fill(100);
        stroke(0);
        for (int i = 0; i <grid.length; i++){
            line(i*boxSize, 0, i*boxSize, screenY);
            
        }
        for (int i = 0; i <grid[0].length; i++){
            line(0, i*boxSize, screenX, i*boxSize);
        }
        if (second()>time){
            
           collisionDet();

        }else if (time==59&&second()<time){
            collisionDet();
            time=0;
        }

        drawPiece(currentPiece);
        drawGrid();
        // fill(255,0,0);
        // String str = currentPiece.getStructure();
        // int value = 0;
        // if (str.indexOf("-")==-1){
        //     value+=Integer.parseInt(str.substring(str.length()-1));
        // }else{
        //     value+=Integer.parseInt(str.substring(str.length()-1))*-1;
        // }
        //square(currentPiece.getPosX()*boxSize, currentPiece.getPosY()*boxSize-value*boxSize, boxSize);
        
		
	}
    public void collisionDet(){
        time = second();
        Boolean collision = false;
        collision = currentPiece.move();

        if (collision){
            addToGrid(currentPiece);
            currentPiece = new Piece(structures.get((int)(Math.random()*structures.size())),3,2,numBoxX,numBoxY,this);
            clearLines();

            
        }
        

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
        String str = p.getStructure();
        // int YOffset = 0;

        // if (str.lastIndexOf(".")>str.lastIndexOf("o")){
        //     YOffset = Integer.parseInt(str.substring(str.lastIndexOf(".")+1));
        // }else{
        //     YOffset = Integer.parseInt(str.substring(str.lastIndexOf("o")+1));

        // }
        int YOffset = p.getYOffset();
        int[] currentPos = {p.getPosX(),p.getPosY()-YOffset};
       

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

        int index = str.indexOf("o");
        while (index!=-1){
            grid[currentPos[0]+index%4][currentPos[1]+index/4]=true;
               
            
            index = str.indexOf("o",index+1);
        
        }
    }



    public void keyPressed(){
        if (key==CODED){
            if (keyCode==UP){
                currentPiece.rotate();
                
            }else if(keyCode==LEFT){
                currentPiece.left();
                
            }
            else if (keyCode==RIGHT){
                currentPiece.right();

            }else if (keyCode==DOWN){
                currentPiece.move();

            }
        } else if (key == ' '){
            currentPiece.space();
        }

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
