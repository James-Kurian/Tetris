
import java.util.ArrayList;
import java.util.Arrays;


public class GeneticAlgorithem {
    public static void main(String[] args) {
        //weightOne = numHoles
        //weightTwo = numBlocks in right space
        //weightThree = smoothness
        //weightFour = elevation
        
        
        
        //ArrayList<ArrayList<Player>> tree = new ArrayList<>();
        
        ArrayList<Player> currentGen = new ArrayList<>();
        double[] bestWeights = new double[4];
        int generation = 0;
        //in each generation we will have the 2 selected parents plus the 2 children with swaped weights plus 2 children with randomly averaged weights
        int generationPop = 6;
        //prevents algorithm from converging at a local optima
        double mutationRate = 0.01;

        //controls accuracy but costs speed
        int numberOfGamesPerPlayer = 10;

        //max and target fitness
        double maxAvg = 0;
        double targetAvg = 500000;

        //set up initial generation
        for (int i = 0; i<generationPop; i++){
            currentGen.add(new Player(Math.random(), Math.random(), Math.random(), Math.random(),numberOfGamesPerPlayer));
        }
        //tree.add(generationZero);

        while (maxAvg<targetAvg){

            
            double[] fitness = new double[currentGen.size()];
            ArrayList<Double> weightedArr = new ArrayList<>();
            double generationFitness = 0;
            int fitnessIndex=0;

            //plays game and evaluates fitness
            for (Player p : currentGen){
                p.play();
                double playerFitness = evaluateFitness(p);
                if (playerFitness>maxAvg){
                    maxAvg=playerFitness;
                    bestWeights=p.getWeights();
                }
                fitness[fitnessIndex] = playerFitness;
                generationFitness+=playerFitness;

                fitnessIndex++;
            }
            // creates a weighted array base on how fit each player is
            double sum = 0;
            for (int i = 0; i<currentGen.size(); i++){
                sum+=fitness[i]/generationFitness;
                weightedArr.add(sum);
            }
            
            //selects the first parent 
            double randVal = Math.random()*sum;
            int firstParentIndex = 0;
            while (randVal>weightedArr.get(firstParentIndex)){
                firstParentIndex++;
            }
            weightedArr.clear();

            //reevauates weights without the first parents fitness
            sum = 0;
            for (int i = 0; i<currentGen.size()-1; i++){
                sum+=fitness[i]/(generationFitness-fitness[firstParentIndex]);
                weightedArr.add(sum);
            }
            

            //sellects second parent
            randVal = Math.random()*sum;
            int secondParentIndex = 0;
            while (randVal>weightedArr.get(secondParentIndex)){
                secondParentIndex++;
            }
            if (secondParentIndex>=firstParentIndex){
                secondParentIndex++;
            }
            ArrayList<Player> nextGeneration = new ArrayList<>();
            Player parentOne = currentGen.get(firstParentIndex);
            Player parentTwo = currentGen.get(secondParentIndex);
            double[] parentOneWeights = parentOne.getWeights();
            double[] parentTwoWeights = parentTwo.getWeights();
            nextGeneration.add(parentOne);
            nextGeneration.add(parentTwo);

            //recombines weights from the two parents
            ArrayList<double[]> newWeights = recombine(parentOneWeights,parentTwoWeights);

            newWeights = tryMutation(newWeights,mutationRate);

            //coded like this just in case i add a couple more weights ¯\_(ツ)_/¯
            for (double[] weights : newWeights) {
                nextGeneration.add(new Player(weights[0], weights[1], weights[2], weights[3], numberOfGamesPerPlayer));

            }

            //another attemp at recombination
            newWeights = recombine(parentOneWeights,parentTwoWeights);

            newWeights = tryMutation(newWeights,mutationRate);
            
            for (double[] weights : newWeights) {
                nextGeneration.add(new Player(weights[0], weights[1], weights[2], weights[3], numberOfGamesPerPlayer));

            }
            //tree.add(nextGeneration);
            currentGen=nextGeneration;
            generation++;

            

            System.out.println("Current generation: " + generation);
            System.out.println("Best Maximum average score: " + maxAvg);
            System.out.println("Maximum average amount score in this generation: " + Arrays.stream(fitness).max().orElse(-1));
            System.out.print("Weights: ");
            System.out.print("{");
            for (double ds : bestWeights) {
                System.out.print(ds + " ");
                
                
            }
            System.out.println("}");
        }
        




        // Instant finish = Instant.now();
        // long timeElapsed = Duration.between(start, finish).toMillis();
        // System.out.println(timeElapsed/1000.0);
        
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



    private static ArrayList<double[]> recombine(double[] parentOneWeights, double[] parentTwoWeights) {
        ArrayList<Integer> indexs = new ArrayList<>();
        for (int i=0;i<parentOneWeights.length;i++){
            indexs.add(i);
            
        }

        //divide lenght by 2 because we swap half of the genes
        for (int i=0; i<parentOneWeights.length/2; i++){
            int index = (int) (Math.random()*indexs.size());
            index = indexs.get(index);
            indexs.remove(Integer.valueOf(index));
            double temp = parentTwoWeights[index];
            parentTwoWeights[index]=parentOneWeights[index];
            parentOneWeights[index]=temp;
        }
        ArrayList<double[]> newSol = new ArrayList<>();
        newSol.add(parentOneWeights);
        newSol.add(parentTwoWeights);
        return newSol;


    }
    // yeah this was a dumb idea plz ignore 
    private static ArrayList<double[]> average(double[] parentOneWeights, double[] parentTwoWeights) {
        ArrayList<Integer> indexs = new ArrayList<>();
        for (int i=0;i<parentOneWeights.length;i++){
            indexs.add(i);
            
        }

        //divide lenght by 2 because we average half of the genes
        for (int i=0; i<parentOneWeights.length/2; i++){
            int index = (int) (Math.random()*indexs.size());
            index = indexs.get(index);
            indexs.remove(Integer.valueOf(index));
            double average = (parentOneWeights[index]+parentOneWeights[index])/2;
            parentOneWeights[index]=average;
            parentTwoWeights[index]=average;
        }
        ArrayList<double[]> newSol = new ArrayList<>();
        newSol.add(parentOneWeights);
        newSol.add(parentTwoWeights);
        return newSol;


    }



    /**
     * 
     * @param p
     * @return the average score of player p
     * 
     */
    private static double evaluateFitness(Player p){
        int totalScore=0;
        ArrayList<Integer> scoresArr = p.getScores();
        for (int score : scoresArr){
            totalScore+=score;
        }
        return totalScore/scoresArr.size();

    }
    
}
