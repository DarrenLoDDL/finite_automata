import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

	public class DFA{
	
	private enum State {
		START, ZERO, DECIMAL, HEADSPACE, MIDSPACE, NUM, STARZERO
		};


	public static boolean containsZero(char c){
		char x = c;
		String nums = "0";
		for (int i = 0 ; i < nums.length() ; i++){
			if (x == nums.charAt(i)){
				return true;
			}
		}
		return false;
	}

	public static boolean isNonZero(char c){
		char x = c;
		String nums = "123456789";
		for (int i = 0 ; i < nums.length() ; i++){
			if (x == nums.charAt(i)){
				return true;
			}
		}
		return false;
	}

	public static boolean isSymbol(char c){
		char x = c;
		String symbols = "+-*/";
		for (int i = 0 ; i < symbols.length() ; i++){
			if (x == symbols.charAt(i)){
				return true;
			}
		}
		return false;
	}

	public static boolean isDecimalPoint(char c){
		char x = c;
		String decimalPoint = ".";
		for (int i = 0 ; i < decimalPoint.length() ; i++){
			if (x == decimalPoint.charAt(i)){
				return true;
			}
		}
		return false;
		
	}
    
    public static List<String> RunDFA(String input) throws NumberException, ExpressionException {

		String read = input;
		List<String> outputs = new ArrayList<>();
		List<String> temp = new ArrayList<>();
		State state = State.START;  
		for(int i = 0 ; i < read.length(); ++i){

			if (isNonZero(read.charAt(i))){ //if input is NOT zero. can follow a decimal or zero. let
				switch (state){
				case START:
					state = State.NUM;
					break;
				case DECIMAL:
					state = State.NUM;
					break;
				case NUM:
					state = State.NUM;
					break;
				case HEADSPACE:
					state = State.NUM;
					break;
				case STARZERO:
					state = State.NUM;
					break;
				default:
					throw new ExpressionException();
				}
                temp.add(String.valueOf(read.charAt(i)));
            }

			if(containsZero(read.charAt(i))){
				switch (state){
				case START:
					state = State.ZERO;
					break;
				case HEADSPACE:
					state = State.ZERO;
					break;
				case DECIMAL:
					state = State.STARZERO;
					break;
				case NUM:
					state = State.STARZERO;
					break;
				case STARZERO:
					state = State.STARZERO;
					break;
				}
                //if the buffer had contents, add to it, else, clear the list, make a new buffer
				if(temp.size()>0){
                    temp.add(String.valueOf(read.charAt(i)));	
                }
                else{
                    temp.clear();
                    temp.add(String.valueOf(read.charAt(i)));
                }
				}

			if(isSymbol(read.charAt(i))){//in a proper expression, there will always be one instance of this. 
            //but the state before this could hvae only been a white spece or a number, whether the add the contents of 
            //the buffer depend on whether it was emptied by a previous state or not. besure not to just add the entire list, 
            //some dumb shit might happen.
				switch(state){
				case ZERO:
					state = State.START;
					break;
				case STARZERO:
					state = State.START;
					break;
				case NUM:
					state = State.START;
					break;
				case DECIMAL:
					state = State.START;
					break;
				case MIDSPACE:
					state = State.START;
					break;
				default:
					throw new ExpressionException();
				}
                if(temp.size()>0){
                String num = "";
					for(String digits : temp){
						num = num + ""+digits;
					}
                outputs.add(num);//important to put the number in before the symbol
                temp.clear();
				}
				outputs.add(String.valueOf(read.charAt(i)));
			}

			if(isDecimalPoint(read.charAt(i))){//should always follow a zero input, dont clear the list
				switch(state){
				case ZERO:
					state = State.DECIMAL;
					break;
				default:
					throw new NumberException();
				}
				temp.add(String.valueOf(read.charAt(i)));	
			}

			if(Character.isWhitespace(read.charAt(i))){//if the list had contrents, ad the contents, else do nothing..
				switch(state){
					case START:
						state = State.HEADSPACE;
						break;
					case HEADSPACE:
						state = State.HEADSPACE;
						break;
					case ZERO:
						state = State.MIDSPACE;
						break;
					case STARZERO:
						state = State.MIDSPACE;
						break;
					case NUM:
						state = State.MIDSPACE;
						break;
					case DECIMAL:
						state = State.MIDSPACE;
						break;
					default:
						throw new ExpressionException();
				}
				if(temp.size()>0){
                String num = "";
					for(String digits : temp){
						num = num + ""+digits;
					}
                outputs.add(num);
				temp.clear();
                }
			}
    }
	
		if(temp.size()>=0){
            String num = "";
			for(String digits : temp){
			num = num + ""+digits;
			}
                outputs.add(num);
				temp.clear();
                }//not sure if this is legal, but this ensures that the last number inputed gets added to the list
				//i guess this also counts as the "buffer" part of the assignment, not sure. You could call it 
				//one of those PDA's , but those are NDFA, and not DFA, idk 
		if(state == State.ZERO|| state == State.STARZERO || state == State.MIDSPACE || 
			 state == State.NUM){
		
		return outputs;
		}
		else{
		if(state == State.START){
			throw new ExpressionException();
		}
		if(state == State.HEADSPACE){
			throw new ExpressionException();
		}
		
		else{
			throw new NumberException();
		}
		}
	}
	}

