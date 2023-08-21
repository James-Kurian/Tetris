
import java.time.LocalTime;
import java.util.ArrayList;




public class Game {
    private int boxSize = 30;
    private int numBoxX = 10;
    private int numBoxY = 24;
    
    boolean running = true;
    private Player player;
    
    
    private ArrayList<String> moves = new ArrayList<>();

    private int screenX = boxSize*numBoxX;
    private int screenY = boxSize*numBoxY;

    
    private int time = getTime();

    private int score=0;
    private int level=0;
    private int totalLinesCleared=0;
    
    
    private boolean[][] grid = new boolean[screenX/boxSize][screenY/boxSize];
    private Piece currentPiece;
    private ArrayList<String[]> structures = new ArrayList<>();
    

    public Game(Player player){
        this.player=player;
        
    }
    public void setup() {
		
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
        int index = (int)(Math.random()*structures.size());
        currentPiece = new Piece(structures.get(index),3,2,numBoxX,numBoxY,this);
        moves.add(index+"");
	}

	public void start(){
        
        while (running){
            
            player.runMove(this);

            int currentTime = getTime();
            

    
            if (currentTime>time){
                moves.add("colDet");
                collisionDet();
               

            }else if (currentTime<time){
                moves.add("colDet");
                collisionDet();
                time=0;
            }
            

            

            
        }
        
        
		
	}
    private int getTime(){
        LocalTime now = LocalTime.now();  
        String time = now.toString() + ".";
        return Integer.parseInt(time.substring(time.lastIndexOf(":")+1,time.indexOf(".")));
        
    }
    public void collisionDet(){
        time = getTime();
        Boolean collision = false;
        collision = currentPiece.move();

        
        

        

        if (collision){
            
           
            

            addToGrid(currentPiece);
            for (boolean[] boolArr : grid){
                for (int i = 0; i<4; i++){
                    if (boolArr[i]){
                        running=false;
                    }
                }
            }
            


            clearLines();
            
            if (running){
                
                //int index = (int)(Math.random()*structures.size());
                ArrayList<String[]> structuresCopy = new ArrayList<>();
                structuresCopy.addAll(structures);
                structuresCopy.remove(currentPiece.getStructures());
                int index = (int)(Math.random()*structuresCopy.size());

                currentPiece = new Piece(structuresCopy.get(index),3,2,numBoxX,numBoxY,this);
                moves.add(index+"");
            }


        }
       

        
    }
        

    

    private void clearLines() {
        int linesCleared=0;

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
            }else{
                linesCleared++;
            }

        }
        grid=newGrid;
        if (linesCleared!=0){
            if (linesCleared==1){
                score+=40*(level+1);
            }else if (linesCleared==2){
                score+=100*(level+1);
            }else if (linesCleared==3){
                score+=300*(level+1);
            }else {
                score+=1200*(level+1);
            }
            level = linesCleared/10;
        }

    }


    private void addToGrid(Piece p) {


        String str = p.getStructure();

        int YOffset = p.getYOffset();
        int[] currentPos = {p.getPosX(),p.getPosY()-YOffset};
       


        int index = str.indexOf("o");
        while (index!=-1){
            grid[currentPos[0]+index%4][currentPos[1]+index/4]=true;
               
            
            index = str.indexOf("o",index+1);
        
        }
    



    }



    public void RotatePiece(){
        moves.add("rotate");
        currentPiece.rotate();
        
    }
    public void movePieceLeft(){
        moves.add("left");
        currentPiece.left();
        
    }
    public void movePieceRight(){
        moves.add("right");
        currentPiece.right();
        
    }
    public void SoftDropPiece(){
        moves.add("move");
        currentPiece.move();
        
    }
    public void HardDropPiece(){
        moves.add("space");
        currentPiece.space();
       
       
    }


    public void drawGrid() {
        boolean[][] gridCopy = new boolean[grid.length][grid[0].length];
        for (int i = 0; i<grid.length; i++) {
            for (int j = 0; j<grid[0].length; j++) {
                gridCopy[i][j]=grid[i][j];
            }
            
        }


        String str = currentPiece.getStructure();
        int YOffset = 0;

        if (str.lastIndexOf(".")>str.lastIndexOf("o")){
            YOffset = Integer.parseInt(str.substring(str.lastIndexOf(".")+1));
        }else{
            YOffset = Integer.parseInt(str.substring(str.lastIndexOf("o")+1));

        }
        int[] currentPos = {currentPiece.getPosX(),currentPiece.getPosY()-YOffset};
       

        for (int i = 0; i <str.length()-1; i++){
            if (str.substring(i, i+1).equals("o")){
                gridCopy[currentPos[0]][currentPos[1]]=true;
            }
            currentPos[0]++;
            if ((i+1)%4==0){
                currentPos[0]=currentPiece.getPosX();
                currentPos[1]++;
            }
            

        }

        

        for (int i = 0; i <gridCopy[0].length; i++){
            for (int j = 0; j<gridCopy.length; j++){
                if (gridCopy[j][i]){
                    System.out.print("o");
                }else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }

    }

    
    public boolean[][] getGrid(){
        return grid;
    }
    public Piece getCurrentPiece(){
        return currentPiece;
    }
    public ArrayList<String> getMoves() {
        return moves;
    }
    public int getScore(){
        return score;
    }
    

}
