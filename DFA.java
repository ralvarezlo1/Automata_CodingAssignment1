import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DFA {
    private Set<String> states;
    private Set<String> alphabet;
    private Map<String, String> transitionFunction;
    private String startState;
    private Set<String> acceptStates;

    public DFA(){
        this.states = new HashSet<String>();
        this.alphabet = new HashSet<String>();
        this.transitionFunction = new HashMap<String, String>();
        this.startState = null;
        this.acceptStates = new HashSet<String>();
    }

    public void addState(String state) throws Exception{
        if (this.states.contains(state)){
            throw new Exception("State " + state + " is already defined.");
        }

        this.states.add(state);
    }

    public void addAlphabetSymbol(String symbol) throws Exception{
        if (this.alphabet.contains(symbol)){
            throw new Exception("Symbol " + symbol + " is already in the alphabet.");
        }

        this.alphabet.add(symbol);
    }

    public void setStartState(String startState) throws Exception{
        if (! this.states.contains(startState)){
            throw new Exception("You need to add state " + startState + " to the DFA before you can set it as the start state.");
        }

        this.startState = startState;
    }

    public void addAcceptState(String acceptState) throws Exception{
        if (! this.states.contains(acceptState)){
            throw new Exception("You need to add state " + acceptState + " to the DFA before you can add it as an accept state.");
        }

        if (this.acceptStates.contains(acceptState)){
            throw new Exception("State " + acceptState + " is already an accept state.");
        }
        this.acceptStates.add(acceptState);
    }

    public void addTransition(String srcState, String symbol, String destState) throws Exception{
        if (! this.states.contains(srcState)){
            throw new Exception("You need to add state " + srcState + " to the DFA before you can use it.");
        }

        if (! this.states.contains(destState)){
            throw new Exception("You need to add state " + destState + " to the DFA before you can use it.");
        }

        if (! this.alphabet.contains(symbol)){
            throw new Exception("You need to add symbol " + symbol + " to the alphabet before you can use it.");
        }

        String srcStateAndSymbol = srcState + ":" + symbol;

        this.transitionFunction.put(srcStateAndSymbol, destState);
    }

    // TODO: Task 2
    public boolean isValid(){

        return true;
    }

    public boolean isAccepted(String inputStr) throws Exception{
        if (! this.isValid()){
            throw new Exception("DFA is not valid - cannot process input string");
        }

        String curState = this.startState;

        for (char symbol : inputStr.toCharArray()){
            String srcStateAndSymbol = curState + ":" + symbol;
            curState = this.transitionFunction.get(srcStateAndSymbol);
        }

        return this.acceptStates.contains(curState);
    }
}
