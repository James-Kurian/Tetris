import java.util.ArrayList;
import java.util.Arrays;




public class Player {
     
    private double weightOne;
    private double weightTwo;
    private double weightThree;
    private double weightFour;
    private int numberOfGames;
    //private ArrayList<ArrayList<String>> moves = new ArrayList<>();
    private ArrayList<Integer> scores = new ArrayList<>();


    public Player(double weightOne,double weightTwo,double weightThree,double weightFour,int numberOfGames ){
        this.weightOne=weightOne;
        this.weightTwo=weightTwo;
        this.weightThree=weightThree;
        this.weightFour=weightFour;
        this.numberOfGames=numberOfGames;
    }

    
    public void play(){
        for (int i = 0; i<numberOfGames; i++){
            Game game = new Game(this);
            game.setup();
            game.start();
            //moves.add(game.getMoves());
            scores.add(game.getScore());
        }
         
    }
    public void runMove(Game game){
        Piece currentPiece = game.getCurrentPiece();
        boolean[][] grid = game.getGrid();

        ArrayList<ArrayList<boolean[][]>> grids = generateGrids(grid, currentPiece);





        ArrayList<Double> costs = generateCosts(grids);

        // i know i could just set the grid to the one thats the lowest cost but like if im going to use this on tetr.io i need INSTRUCTIONS
        int minValIndex = 0;
        for (int i=0;i<costs.size();i++){
            if (costs.get(i)<costs.get(minValIndex)){
                minValIndex=i;
            }
        }

        
        int length = 0;
        int numRotations = 0;
        for (ArrayList<boolean[][]> rotation : grids){

            length+=rotation.size();
            if (minValIndex>=length){
                numRotations++;
                

            }else{
                length-=rotation.size();

                break;
            }
        }
        int moveX=0;
        for (int i = 0; i <numRotations; i++){
            game.RotatePiece();
        }
        moveX = (((currentPiece.startOfPiece(currentPiece.getStructure())*-1))+(minValIndex-length))-currentPiece.getPosX();
        if (moveX<0){
            for (int i = 0; i < Math.abs(moveX); i++) {
                game.movePieceLeft();
                
            }
        } else{
            for (int i = 0; i < moveX; i++) {
                game.movePieceRight();
            }
        }


        game.HardDropPiece();



    }





    private boolean compGrids(boolean[][] gridOne, boolean[][] gridTwo) {
        for (int x = 0; x < gridOne.length; x++){
            for (int y = 0; y < gridOne[0].length; y++) {
                if (!gridOne[x][y]==gridTwo[x][y]){
                    

                    return false;



                }
                
            }
        }
        return true;

    }


    private ArrayList<Double> generateCosts(ArrayList<ArrayList<boolean[][]>> grids) {

        ArrayList<Double> costs = new ArrayList<>();
        
        for (ArrayList<boolean[][]> rotation : grids){
        
            for (boolean[][] grid : rotation){

            //number of holes
                int numHoles=0;
                int numBoxesRight=0;
                int smoothness=0;
                int elevation=0;
                
                for(int x = 0; x< grid.length; x++){
                    boolean roof = false;
                    for (int y = 0; y<grid[x].length; y++){
                        if (!roof&&grid[x][y]){
                            roof=true;
                        }else if(roof && !grid[x][y]){
                            numHoles++;
                        }
                    }
                }

            //number of blocks in the right most space
            for (int i = 0; i <grid[grid.length-1].length; i++){
                if (grid[grid.length-1][i]){
                    numBoxesRight++;
                }
            }

            //smoothness and elevation
            int[] yElevations = new int[grid.length];
            int index =0;
            for(int x = 0; x< grid.length; x++){
                boolean notFound = true;
                for (int y = 0; y<grid[x].length&&notFound; y++){
                    if (grid[x][y]){
                        notFound=false;
                        yElevations[index]=y;
                        index++;
                    }

                }
                if (notFound){
                    yElevations[index]=grid[0].length-1;
                    index++;
                }
                
            }
            int minElev = yElevations[0];
            for (int y = 0; y<yElevations.length-1;y++){
                if (minElev>yElevations[y]){
                    minElev=yElevations[y];
                }
                smoothness+=Math.abs(yElevations[y]-yElevations[y+1]);
            }
            if (minElev>yElevations[yElevations.length-1]){
                minElev=yElevations[yElevations.length-1];
            }
            elevation=grid[0].length-minElev;

            //adds cost to array
            costs.add(weightOne*numHoles+weightTwo*numBoxesRight+weightThree*smoothness+ weightFour*elevation);
            

        } 
    }    
    return costs;
}
        
      

        
    


    public ArrayList<ArrayList<boolean[][]>> generateGrids(boolean[][] grid, Piece currenPiece){
        
        
        
        ArrayList<ArrayList<boolean[][]>> grids = new ArrayList<ArrayList<boolean[][]>>();
        Piece piece = new Piece(currenPiece);

        for (String structure : piece.getStructures()){
            // ten is the number of boxes on the x axis
            

            ArrayList<boolean[][]> gridAtRotation = new ArrayList<boolean[][]>();

            boolean isDuplicate = false;
            for (int i = (piece.startOfPiece(structure)*-1); i<=grid.length-piece.endOfPiece(structure) && !isDuplicate;i++){
                

            
                //sets piece to corrent y pos
                int posX = i;
                int posY = piece.getPosY();
                int YOffset = piece.getYOffset();
                if (!piece.validMove(structure, new int[] {posX,posY-YOffset+1}, grid)){

                }
                while (piece.validMove(structure, new int[] {posX,posY-YOffset+1}, grid)){
                    posY++;
                }

                
                //create grid copy
                boolean[][] newGrid = new boolean[grid.length][grid[0].length];
                for (int j = 0; j<newGrid.length;j++){
                    newGrid[j]=Arrays.copyOf(grid[j], grid[j].length);
                }
                


                //add piece to grid
                int[] currentPos = {posX,posY-YOffset};
                int index = structure.indexOf("o");
                while (index!=-1){
                    newGrid[currentPos[0]+index%4][currentPos[1]+index/4]=true;
                    index = structure.indexOf("o",index+1);
                }
                
                //clear lines
                index = (newGrid[0].length)-1;
                Boolean changeIndex = false;
                boolean[][] newNewGrid = new boolean[newGrid.length][newGrid[0].length];
                for (int j = newGrid[0].length-1; j >= 0 ; j--){
                    changeIndex = false;
                    for (int k = 0; k<newGrid.length; k++){
                        if (!newGrid[k][j]){
                            changeIndex=true;
                        }
                        newNewGrid[k][index]=newGrid[k][j];
                    }
                    if (changeIndex){
                        index--;
                    }
        
                }
                if (i==(piece.startOfPiece(structure)*-1) && grid.length>0){
                    for (ArrayList<boolean[][]> arr : grids){
                        isDuplicate = compGrids(newNewGrid, arr.get(0));

                    }
                    
                }

                
                


                gridAtRotation.add(newNewGrid);



            }
            grids.add(gridAtRotation);

        }



        return grids;
    }


    // public ArrayList<ArrayList<String>> getMoves() {
    //     return moves;
    // }
    public double[] getWeights(){
        return new double[] {weightOne,weightTwo,weightThree,weightFour};
    }


    public ArrayList<Integer> getScores() {
        return scores;
    }
    
}
