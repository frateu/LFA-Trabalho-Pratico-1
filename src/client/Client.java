package client;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import apps.*;

public class Client {

	public static void main(String[] args) {

		Deterministico deterministico = new Deterministico();
		NaoDeterministico naoDeterministico = new NaoDeterministico();
		ArrayList<String> alphabet = new ArrayList<>();
		ArrayList<ArrayList<String>> automaton = new ArrayList<>();
		String initState;
		ArrayList<String> word = new ArrayList<>();
		
		JOptionPane.showMessageDialog(null, "Teste de Autômato!");
		
		String opt = JOptionPane.showInputDialog("Opções:\n\n D - Deterministico\n N - Não Deterministico\n\nEscolha uma das opções disponiveis");
		
		if (opt.equals("d") || opt.equals("D")) {
			alphabet = deterministico.alphabetDef();
			word = deterministico.wordDef();
			initState = deterministico.initState();
			
			deterministico.verfWord(word, initState, deterministico.finalState(), deterministico.automatonDef(deterministico.transCount(), alphabet));
		}else if (opt.equals("n") || opt.equals("N")) {
			alphabet = deterministico.alphabetDef();
			word = deterministico.wordDef();
			int numStates = naoDeterministico.statesCount();
			int transNum = deterministico.transCount();
			automaton = deterministico.automatonDef(transNum, alphabet);
			ArrayList<ArrayList<String>> allStates = naoDeterministico.sortedSetTrans(naoDeterministico.genAllStates(automaton, numStates));
			initState = deterministico.initState();
			ArrayList<String> newFinalStates = naoDeterministico.newFinalStates(allStates, deterministico.finalState());
			ArrayList<ArrayList<String>> newAutomaton = naoDeterministico.tableGen(allStates, naoDeterministico.organizeNewStates(allStates, numStates), automaton, alphabet, numStates);
			
			deterministico.verfWord(word, initState, newFinalStates, newAutomaton);
			
		}else {
			JOptionPane.showMessageDialog(null, "Opção invalida!");
		}
	}

}
