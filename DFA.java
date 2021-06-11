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
//  If there is no start state (remember that the start state is stored in this.startState), return False
//        System.out.println("The start state is: "+this.startState);
        if (this.startState == null) {return false;}
//	If the start state is not one of the states contained in this.states, return False
//        System.out.print("The states are: ");
//        for (String s : this.states){
//            System.out.print(s+"\t");
//        }
//        System.out.println("");
        if(!this.states.contains(this.startState)) {return false;}
//  If any of the accept states is not one of the states contained in this.states, return False
//        System.out.print("The accept states are: ");
//        for (String aS : this.acceptStates){
//            System.out.print(aS+"\t");
//        }
//        System.out.println("");
        for (String s : this.acceptStates){
            if (!this.states.contains(s)) {return false;}
        }
//  Every state in self.states should have a transition for every symbol in self.alphabet. If there are missing transitions in this.transitionFunction, return False
//        System.out.print("The transition keys are: ");
//        for (String kS : this.transitionFunction.keySet()){
//            System.out.print(kS+"\t");
//        }
//        System.out.println("");
        for (String s : this.states){
            for (String a : this.alphabet){
                if(!this.transitionFunction.containsKey(s+":"+a)) {return false;}
            }
        }
//  If there are invalid transitions in this.transitionFunction, return False. A transition is invalid if: 1) the source state is not in this.states, and/or
//  2) the symbol is not in self.alphabet, and/or 3) the destination state is not in this.states.
//        System.out.print("The transition values are: ");
//        for (String kV : this.transitionFunction.values()){
//            System.out.print(kV+"\t");
//        }
//        System.out.println("");
        for (String k : this.transitionFunction.keySet()){
            if(!this.states.contains(k.substring(0,k.indexOf(":")))) {return false;}
            if(!this.alphabet.contains(k.substring(k.indexOf(":")+1))) {return false;}
        }
        for (String v : this.transitionFunction.values()){
            if(!this.states.contains(v)) {return false;}
        }
//  Return True if none of the previous conditions were met.
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
