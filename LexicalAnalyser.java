import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class LexicalAnalyser {

	
	public static List<Token> analyse(String input) throws NumberException, ExpressionException, NumberFormatException {
		
		String userInput = input;
		List<Token> TokenList = new ArrayList<>();
		//run the initial input through the dfa and check for errors
		List<String> expression = DFA.RunDFA(input); // expression = {123, + , 1231}
		for(String term : expression){
			System.out.print(term);
		}
		//TokenBuffer.RunBuffer(userInput);
			for(int i = 0; i < expression.size() ; ++i){
			try	{
			Token token = new Token(Double.parseDouble(expression.get(i)));
			TokenList.add(token);
			}
			catch(NumberFormatException e){
			Token token = new Token(Token.typeOf((expression.get(i)).charAt(0)));
			TokenList.add(token);
			}
			}
			return TokenList;
	}
}


	



		

	

	//Original Methd
	//public static List<Token> analyse(String input) throws NumberException, ExpressionException {
		
	//	}

	//	return Collections.emptyList();

	//	}
//Method that analyses the the inpout and converts it to TokenList
//	public static List<Token> analyse(String input) throws NumberException, ExpressionException {
		//a TokenType is just a property of Token.
	//	String str = input;
		//List<Token> TokenList = new ArrayList<>();
	//	for(int i = 0; i < str.length();++i){
	//		Token token = new Token(Token.typeOf(str.charAt(i)));
	//		TokenList.add(token);
//		}
//		return TokenList;

//		}

