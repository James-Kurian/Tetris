import java.util.ArrayList;

public class getMoves {
    public static void main(String[] args) {
        double[] weights = {0.37806321127722153, -0.13675301143031393 ,0.050790816242396675, 0.17307947299348392 };
        //{0.8173804240846771, -0.15195280343043005, 0.14392672603966594, 0.21731981927239574 };
        Player p = new Player(weights[0], weights[1], weights[2], weights[3], 1);
        p.play();
        //ArrayList<ArrayList<String>> moves = p.getMoves();
        //ArrayList<String> move = moves.get(0);
        //System.out.println(move);
        //System.out.println("moves: "+move.size());
        System.out.println("score was: " + p.getScores().get(0));
    }
    
}
