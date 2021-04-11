package client;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import apps.*;

public class Client {

	public static void main(String[] args) {

		Deterministico deterministico = new Deterministico();
		NaoDeterministico naoDeterministico = new NaoDeterministico();
		ArrayList<String> alphabet = new ArrayList<>();
		ArrayList<ArrayList<String>> allStates = new ArrayList<>();
		ArrayList<ArrayList<String>> automaton = new ArrayList<>();
		
		JOptionPane.showMessageDialog(null, "Teste de Autômato!");
		
		String opt = JOptionPane.showInputDialog("Opções:\n\n D - Deterministico\n N - Não Deterministico\n\nEscolha uma das opções disponiveis");
		
		if (opt.equals("b") || opt.equals("B")) {
			
		}else if (opt.equals("n") || opt.equals("N")) {
			alphabet = deterministico.alphabetDef();
			int numStates = naoDeterministico.statesCount();
			int transNum = deterministico.transCount();
			automaton = deterministico.automatonDef(transNum, alphabet);
			allStates = naoDeterministico.sortedSetTrans(naoDeterministico.genAllStates(automaton, numStates));
			
			naoDeterministico.tableGen(allStates, naoDeterministico.organizeNewStates(allStates, numStates), automaton, alphabet, numStates);
		}else {
			JOptionPane.showMessageDialog(null, "Opção invalida!");
		}
	}

}
