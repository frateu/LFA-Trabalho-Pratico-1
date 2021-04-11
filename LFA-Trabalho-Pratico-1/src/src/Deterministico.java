package src;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Deterministico {

	public static int transCount() {

		int transNum = 0;

		do {
			transNum = Integer.parseInt(JOptionPane.showInputDialog("Insira o numero de transições do autômato: "));

			if (transNum == 0) {
				JOptionPane.showMessageDialog(null, "Por favor insira um valor valido.");
			}
		} while (transNum == 0);

		return transNum;
	}

	public static ArrayList<String> alphabetDef() {
		JOptionPane.showMessageDialog(null, "Construção do alfabeto!");

		ArrayList<String> alphabet = new ArrayList<>();

		int numChar = 0;

		do {
			numChar = Integer.parseInt(JOptionPane.showInputDialog("Insira quantos caracteres tem no alfabeto: "));

			if (numChar == 0) {
				JOptionPane.showMessageDialog(null, "Por favor insira um valor valido.");
			}
		} while (numChar == 0);

		for (int i = 0; i < numChar; i++) {
			String caracter = "";
			do {
				caracter = JOptionPane.showInputDialog("Insira um caracter para o alfabeto: ");
			} while (caracter.equals(""));
			alphabet.add(caracter);
		}

		return alphabet;

	}

	public static ArrayList<ArrayList<String>> automatonDef(int transNum, ArrayList<String> alphabet) {
		JOptionPane.showMessageDialog(null, "Construção do autômato!");

		ArrayList<ArrayList<String>> automaton = new ArrayList<>();
		String stateIni = "";
		String stateFinal = "";
		String transChar = "";
		int verfAlphabet = 0;

		for (int i = 0; i < transNum; i++) {
			ArrayList<String> listStates = new ArrayList<>();

			do {
				stateIni = JOptionPane.showInputDialog("Insira o estado do qual está se referindo: ");

				if (stateIni.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor insira um valor.");
				}
			} while (stateIni.equals(""));

			do {
				do {
					transChar = JOptionPane
							.showInputDialog("Informe qual caracter do alfabeto é responsavel por essa transição: ");

					if (transChar.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor insira um valor.");
					}
				} while (transChar.equals(""));

				for (int j = 0; j < alphabet.size(); j++) {
					String caracter = alphabet.get(j);
					if (caracter.equals(transChar)) {
						verfAlphabet = 1;
						break;
					}
				}

				if (verfAlphabet == 0) {
					JOptionPane.showMessageDialog(null,
							"Esse caracter de transição não pertence ao alfabeto. Por favor, insira um caracter valido.");
				}
			} while (verfAlphabet == 0);

			do {
				stateFinal = JOptionPane.showInputDialog("Insira para qual estado deja ir com a transição: ");

				if (stateFinal.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor insira um valor.");
				}
			} while (stateFinal.equals(""));

			listStates.add(stateIni);
			listStates.add(transChar);
			listStates.add(stateFinal);

			automaton.add(listStates);

		}

		return automaton;

	}

	public static ArrayList<String> wordDef() {
		ArrayList<String> word = new ArrayList<>();
		String caracter = " ";

		JOptionPane.showMessageDialog(null, "Construção da palavra!");

		do {
			caracter = JOptionPane
					.showInputDialog("Insira um caracter por vez!\nPara finalizar insira não insira valor. ");

			if (!caracter.equals("")) {
				word.add(caracter);
			}
		} while (!caracter.equals(""));

		return word;
	}

	public static String initState() {
		String initState = null;
		do {
			initState = JOptionPane.showInputDialog("Insira o o estado inicial do automato: ");

			if (initState.equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor, insira um valor.");
			}
		} while (initState.equals(""));

		return initState;
	}

	public static ArrayList<String> finalState() {
		ArrayList<String> finalState = new ArrayList<>();
		int numFinalStates = 0;

		do {
			String nfs = "";
			nfs = JOptionPane.showInputDialog("Insira quantos estados finais existem no automato: ");

			if (nfs.equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor, insira um valor.");
			} else {
				numFinalStates = Integer.parseInt(nfs);

				if (numFinalStates <= 0) {
					JOptionPane.showMessageDialog(null, "Deve pelo menos existir um estado final.");
				}
			}

		} while (numFinalStates <= 0);

		for (int i = 0; i < numFinalStates; i++) {
			String fs;
			do {
				fs = JOptionPane.showInputDialog("Insira um estado final do automato: ");

				if (fs.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, insira um valor.");
				}
			} while (fs.equals(""));

			finalState.add(fs);
		}

		return finalState;
	}

	public static void verfWord(ArrayList<String> word, String initState, ArrayList<String> finalState,
			ArrayList<ArrayList<String>> automaton) {
		String actualState = initState;
		int verfWordLimit = 0;

		String currentTrans = "";

		for (String w : word) {
			for (int x = 0; x < automaton.size(); x++) {
				for (int y = 0; y < 3; y++) {
					ArrayList<String> partialList = automaton.get(x);
					if (partialList.get(0).equals(actualState)) {
						currentTrans = partialList.get(1);
						if (currentTrans.equals(w)) {
							actualState = partialList.get(2);
							verfWordLimit = 0;
						}
					} else if (!currentTrans.equals(w)) {
						verfWordLimit = 1;
					}
				}
			}
		}

		int verfFinal = 0;

		for (String sf : finalState) {
			if (actualState.equals(sf) && verfWordLimit == 0) {
				JOptionPane.showMessageDialog(null, "Resultado:\nEssa palavra pertence a esse autômato");
				verfFinal = 1;
			}
		}

		if (verfFinal == 0) {
			JOptionPane.showMessageDialog(null, "Resultado:\nEssa palavra não pertence a esse autômato");
		}

		System.out.println("Autômato: " + automaton + "\n\nPalavra: " + word);

	}

}
