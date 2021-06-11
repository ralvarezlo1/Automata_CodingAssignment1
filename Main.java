import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        runSampleDFATests();
        runAssignmentDFATests();
        runDFAValidationTests();

    }

    public static void runSampleDFATests() throws Exception {
        // DFA that recognizes the language of all
        // binary numbers that end with a 1
        DFA dfa1 = new DFA();

        dfa1.addState("q0");
        dfa1.addState("q1");

        dfa1.addAlphabetSymbol("0");
        dfa1.addAlphabetSymbol("1");

        dfa1.setStartState("q0");

        dfa1.addAcceptState("q1");

        dfa1.addTransition("q0", "0", "q0");
        dfa1.addTransition("q0", "1", "q1");

        dfa1.addTransition("q1", "0", "q0");
        dfa1.addTransition("q1", "1", "q1");

        System.out.println("\n--- Sample DFA Tests ---");
        System.out.println("Result of processing the empty string: " + dfa1.isAccepted(""));
        System.out.println("Result of processing 0110: " + dfa1.isAccepted("0110"));
        System.out.println("Result of processing 1111: " + dfa1.isAccepted("1111"));
        System.out.println("Result of processing 000001: " + dfa1.isAccepted("000001"));
        System.out.println("Result of processing 1100100: " + dfa1.isAccepted("1100100"));
        System.out.println("Result of processing 11001001: " + dfa1.isAccepted("11001001"));

    }

    // TODO: Task 1
    public static void runAssignmentDFATests() throws Exception{
        System.out.println("\n--- Assignment DFA Tests ---");

//        Create a new DFA
        DFA customDfa = new DFA();

//        Declare the states of the DFA
        customDfa.addState("s");
        customDfa.addState("q1");
        customDfa.addState("q2");
        customDfa.addState("f");

//        Declare the symbols in the language
        customDfa.addAlphabetSymbol("a");
        customDfa.addAlphabetSymbol("b");

//        Define the start state
        customDfa.setStartState("s");

//        Define the accept state
        customDfa.addAcceptState("f");

//        Create the transitions from s
        customDfa.addTransition("s", "a", "q1");
        customDfa.addTransition("s", "b", "q2");

//        Create the transitions from q1
        customDfa.addTransition("q1", "a", "q1");
        customDfa.addTransition("q1", "b", "f");

//        Create the transitions from q2
        customDfa.addTransition("q2", "a", "f");
        customDfa.addTransition("q2", "b", "q2");

//        Create the transitions from f
        customDfa.addTransition("f", "a", "f");
        customDfa.addTransition("f", "b", "f");

//        Print the values of the tests
        System.out.println("\n--- Sample DFA Tests ---");
        System.out.println("Result of processing 'ba': " + customDfa.isAccepted("ba") + "\t\t\t\t\t\t\t\t\t(the expected value is true)");
        System.out.println("Result of processing 'ab': " + customDfa.isAccepted("ab") + "\t\t\t\t\t\t\t\t\t(the expected value is true)");
        System.out.println("Result of processing 'aaaab': " + customDfa.isAccepted("aaaab") + "\t\t\t\t\t\t\t\t(the expected value is true)");
        System.out.println("Result of processing 'bbba': " + customDfa.isAccepted("bbba") + "\t\t\t\t\t\t\t\t(the expected value is true)");
        System.out.println("Result of processing 'aabba': " + customDfa.isAccepted("aabba") + "\t\t\t\t\t\t\t\t(the expected value is true)");
        System.out.println("Result of processing 'bbbb': " + customDfa.isAccepted("bbbb") + "\t\t\t\t\t\t\t\t(the expected value is false)");
        System.out.println("Result of processing 'aaaa': " + customDfa.isAccepted("aaaa") + "\t\t\t\t\t\t\t\t(the expected value is false)");
        System.out.println("Result of processing the empty string: " + customDfa.isAccepted("") + "\t\t\t\t\t(the expected value is false)");
        System.out.println("Result of processing 'a': " + customDfa.isAccepted("a") + "\t\t\t\t\t\t\t\t\t(the expected value is false)");
        System.out.println("Result of processing 'b': " + customDfa.isAccepted("b") + "\t\t\t\t\t\t\t\t\t(the expected value is false)");
    }

    public static void runDFAValidationTests() throws Exception {
        System.out.println("\n--- DFA Validation Tests ---");

        // Test 1 - DFA with no start state

        DFA dfa1 = new DFA();

        dfa1.addState("q0");
        dfa1.addState("q1");

        dfa1.addAlphabetSymbol("0");
        dfa1.addAlphabetSymbol("1");

        dfa1.addAcceptState("q1");

        dfa1.addTransition("q0", "0", "q0");
        dfa1.addTransition("q0", "1", "q1");

        dfa1.addTransition("q1", "0", "q0");
        dfa1.addTransition("q1", "1", "q1");

        System.out.println("Is DFA1 valid (result should be false)?: " + dfa1.isValid());

        // Test 2 - DFA with missing transitions

        DFA dfa2 = new DFA();

        dfa2.addState("q0");
        dfa2.addState("q1");

        dfa2.addAlphabetSymbol("0");
        dfa2.addAlphabetSymbol("1");

        dfa2.setStartState("q0");
        dfa2.addAcceptState("q1");

        dfa2.addTransition("q0", "0", "q0");
        dfa2.addTransition("q1", "0", "q0");

        //Erase this....
//        dfa2.addTransition("q0", "1", "q1");
//        dfa2.addTransition("q1", "1", "q1");
        //All the way to here...

        System.out.println("Is DFA2 valid (result should be false)?: " + dfa2.isValid());

        // Feel free to add other tests
//        Create a new DFA
        DFA customDfa = new DFA();

//        Declare the states of the DFA
        customDfa.addState("s");
        customDfa.addState("q1");
        customDfa.addState("q2");
        customDfa.addState("f");

//        Declare the symbols in the language
        customDfa.addAlphabetSymbol("a");
        customDfa.addAlphabetSymbol("b");

//        Define the start state
        customDfa.setStartState("s");

//        Define the accept state
        customDfa.addAcceptState("f");

//        Create the transitions from s
        customDfa.addTransition("s", "a", "q1");
        customDfa.addTransition("s", "b", "q2");

//        Create the transitions from q1
        customDfa.addTransition("q1", "a", "q1");
        customDfa.addTransition("q1", "b", "f");

//        Create the transitions from q2
        customDfa.addTransition("q2", "a", "f");
        customDfa.addTransition("q2", "b", "q2");

//        Create the transitions from f
        customDfa.addTransition("f", "a", "f");
        customDfa.addTransition("f", "b", "f");

//        Verify if it's valid
        System.out.println("Is customDFA valid (result should be true)?: " + customDfa.isValid());
    }




}
