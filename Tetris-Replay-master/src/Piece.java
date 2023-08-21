
public class Piece {
    private int rotation = 0;
    private String[] structure;
    private int posX;
    private int posY;
    private int numBoxX = 10;
    private int numBoxY = 24;
    private int YOffset;
    private Game main;

    public Piece(String[] structure,int posX, int posY, int boxX, int boxY, Game main){
        this.structure=structure;
        this.posX=posX;
        this.posY=posY;
        numBoxX=boxX;
        numBoxY=boxY;
        this.main=main;
        
        if (structure[0].lastIndexOf(".")>structure[0].lastIndexOf("o")){
            this.YOffset = Integer.parseInt(structure[0].substring(structure[0].lastIndexOf(".")+1));
        }else{
            this.YOffset = Integer.parseInt(structure[0].substring(structure[0].lastIndexOf("o")+1));

        }

        
    }
    public String getStructure(){
        return structure[rotation];
    }


    public void rotate(){
        int posXBeforeRotation = posX;
        int posYBeforeRotation = posY;
        int YOffsetBeforeRotation = YOffset;

        if (rotation==structure.length-1){
            rotation=0;
        }else{
            rotation++;
        }





        if (posX<0){
            posX=0;
        } else if (posX+endOfPiece(structure[rotation])>numBoxX){
            posX=numBoxX-(endOfPiece(structure[rotation]));
        }
        if (structure[rotation].lastIndexOf(".")>structure[rotation].lastIndexOf("o")){
            this.YOffset = Integer.parseInt(structure[rotation].substring(structure[rotation].lastIndexOf(".")+1));
        }else{
            this.YOffset = Integer.parseInt(structure[rotation].substring(structure[rotation].lastIndexOf("o")+1));

        } if (posY+getHeight()>numBoxY){
            posY = numBoxY-getHeight();
        }


        boolean[][] grid = main.getGrid();

        String str = structure[rotation];
        int subVal = 1;
        if (str.indexOf("-")!=-1){
            subVal=2;

        }
        int rotatedYOffset = 0;
        if (str.lastIndexOf(".")>str.lastIndexOf("o")){
            rotatedYOffset = Integer.parseInt(str.substring(str.lastIndexOf(".")+1));
        }else{
            rotatedYOffset = Integer.parseInt(str.substring(str.lastIndexOf("o")+1));

        }
        str = str.substring(0, str.length()-subVal);
        
        int[] currentPos = {posX,posY-rotatedYOffset};
        //int index = str.indexOf("o");
        if (!validMove(str, currentPos, grid)){
            if (rotation==0){
                rotation=structure.length-1;
                }else{
                    rotation--;
                }
                posX=posXBeforeRotation;
                posY=posYBeforeRotation;
                YOffset=YOffsetBeforeRotation;
        }






    }
    public int startOfPiece(String str){
        int subVal = 1;
        if (str.indexOf("-")!=-1){
            subVal=2;

        }
        str=str.substring(0,str.length()-subVal);
        for (int i = 0; i <4; i++){
            for (int j = 0; j < str.length(); j+=4) {
                if(str.substring(i+j,i+j+1).equals("o")){
                    return i;
                };
            }
        }
        return -1;
    }

    public boolean move() {
        posY++;
        boolean[][] grid = main.getGrid();
        String str = structure[rotation];

        int subVal = 1;
        if (str.indexOf("-")!=-1){
            subVal=2;

        }
        
        int[] currentPos = {posX,posY-YOffset};
        if (currentPos[1]+getHeight()>numBoxY){
            posY = numBoxY-getHeight()+YOffset;
            return true;
        }
       
        str = str.substring(0, str.length()-subVal);

        if (!validMove(str, currentPos, grid)){
            posY--;
            return true;
        }
        return false;
        
    }
    public void left() {
        if(posX+startOfPiece(structure[rotation])>0){

            posX--;
            boolean[][] grid = main.getGrid();

            String str = structure[rotation];
            
            int[] currentPos = {posX,posY-YOffset};
    
            
            if (!validMove(str, currentPos, grid)){
                posX++;
            }
            
        }
        
    }
    public void right() {
        if (posX+endOfPiece(structure[rotation])<numBoxX){
            posX++;

            boolean[][] grid = main.getGrid();

            String str = structure[rotation];
            
            int[] currentPos = {posX,posY-YOffset};
    
            if (!validMove(str, currentPos, grid)){
                posX--;
            }
            
        }
        
    }
    private int endOfPiece(String str) {
        int subVal = 1;
        if (str.indexOf("-")!=-1){
            subVal=2;

        }
        str=str.substring(0,str.length()-subVal);
        int index = str.indexOf("o");
        int length = 0;

        while (index!=-1){
            if (length<index%4){
                length = index%4;
            }
            index = str.indexOf("o",index+1);
        }
        return length+1;
        
    }

    private boolean validMove(String shape, int[] currentPos, boolean[][] grid){
        int index = shape.indexOf("o");
        while (index!=-1){
            if (grid[currentPos[0]+index%4][currentPos[1]+index/4]){
                return false;
            }
            index = shape.indexOf("o",index+1);
        
        }
        return true;
    }



    public void space(){
        Boolean collision = false;
           
    
        while (!collision){
            collision = move();
        }
        main.collisionDet();
        
        
    }

    public int getYOffset(){
        return YOffset;
    }
    public void setY(int y){
        this.posY=y;
    }
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    public int getHeight(){
        int subVal = 1;
        if (structure[rotation].indexOf("-")!=-1){
            subVal=2;

        }
        return ((structure[rotation].length()-subVal)/4);
    }
}
