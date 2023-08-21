public class test {
    public static void main(String[] args) {
        String str = "aaaabbbbccccdddd";
        String thing = "";


        for (int i = 0; i < 4; i++){
            for (int j = str.length()-1; j > 0 ; j-=4) {
                thing += str.substring(j-i,j-i+1);
            }
        }
    }
    
}
