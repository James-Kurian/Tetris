import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        double mutationRate = 0.01;
        double[] one = new double[] {0.4,0.1,0.1,0.9};
        double[] two = new double[] {0.2,0.7,0.6,0.1};

        // double test = Arrays.stream(one).max().orElse(-1);
        // System.out.println(test);

        LocalTime now = LocalTime.now();  
        String time = now.toString();
        System.out.println(time);
        // ArrayList<double[]> genWeights = new ArrayList<>();
        // genWeights.add(one);
        // genWeights.add(two);

        // for (int i=0; i<100; i++){
        //     genWeights = tryMutation(genWeights, mutationRate);
        //     for (double[] arr : genWeights) {
        //         System.out.print("{");
        //         for (double ds : arr) {
        //             System.out.print(ds + " ");
        //         }
        //         System.out.print("}");
        //     }
        //     System.out.println();
            
        // }
        

    //     boolean[][] grid = {{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
    //     },{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,true,true,true
    //     },{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,true
    //     },{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,true
    //     },{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,true,false,true
    //     },{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,true,true,true
    //     },{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,false,true,true
    //     },{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,true,false,true
    //     },{false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,true,true,true,true,true
    //     },{false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,false,false,false
    //     }};
        
    //         for (int i = 0; i <grid[0].length; i++){
    //             for (int j = 0; j<grid.length; j++){
    //                 if (grid[j][i]){
    //                     System.out.print("o");
    //                 }else{
    //                     System.out.print(".");
    //                 }
    //             }
    //             System.out.println();
    //         }
    //         System.out.println("__________");

    //     int numHoles=0;
    //     int numBoxesRight=0;
    //     int smoothness=0;
    //     int elevation=0;
    //     for(int x = 0; x< grid.length; x++){
    //         boolean roof = false;
    //         for (int y = 0; y<grid[x].length; y++){
    //             if (!roof&&grid[x][y]){
    //                 roof=true;
    //             }else if(roof && !grid[x][y]){
    //                 numHoles++;
    //             }
    //         }
    //     }
    //     for (int i = 0; i <grid[grid.length-1].length; i++){
    //         if (grid[grid.length-1][i]){
    //             numBoxesRight++;
    //         }
    //     }

    //     int[] yElevations = new int[grid.length];
    //     int index =0;
    //     for(int x = 0; x< grid.length; x++){
    //         boolean notFound = true;
    //         for (int y = 0; y<grid[x].length&&notFound; y++){
    //             if (grid[x][y]){
    //                 notFound=false;
    //                 yElevations[index]=y;
    //                 index++;
    //             }

    //         }
    //         if (notFound){
    //             yElevations[index]=grid[0].length-1;
    //             index++;
    //         }
            
    //     }
    //     int minElev = yElevations[0];
    //     for (int y = 0; y<yElevations.length-1;y++){
    //         if (minElev>yElevations[y]){
    //             minElev=yElevations[y];
    //         }
    //         smoothness+=Math.abs(yElevations[y]-yElevations[y+1]);
    //     }
    //     if (minElev>yElevations[yElevations.length-1]){
    //         minElev=yElevations[yElevations.length-1];
    //     }
    //     elevation=grid[0].length-minElev;
        
        
        
        
    
    //     System.out.println(numHoles);
    //     System.out.println(numBoxesRight);
    //     System.out.println(smoothness);
    //     System.out.println(elevation);
        
    }
    private static ArrayList<double[]> tryMutation(ArrayList<double[]> genWeights, double mutationRate) {
        for (double[] weights : genWeights){
            for (int i = 0; i < weights.length; i++) {
                double mutate = Math.random();
                if (mutate<mutationRate){
                    weights[i]=(Math.random()*2)-1;
                    
                }
            }
        }
        return genWeights;
    }

    
}
