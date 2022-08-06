public class Runner {
    public static void main(String[] args) {
        try {
            System.out.println(LexicalAnalyser.analyse("1/231/13415/5747 +2- 0.23412 +1"));
        }
        catch (NumberException e) {

        }
        catch (ExpressionException e) {
            
        }
    }
}
