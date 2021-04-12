package apps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.JOptionPane;

import apps.Deterministico;

public class NaoDeterministico {

	public static ArrayList<String> removeRepp(ArrayList<String> list) {
		ArrayList<String> l = new ArrayList<>();

		for (String i : list) {
			if (!l.contains(i)) {
				l.add(i);
			}
		}

		Collections.sort(l);

		return l;
	}

	public static int statesCount() {
		int numStates = 0;

		do {
			String ns = "";

			ns = JOptionPane.showInputDialog("Insira quantos estados exitem no autômato: ");

			if (ns.equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor, insira um valor.");
			} else {
				numStates = Integer.parseInt(ns);

				if (numStates <= 0) {
					JOptionPane.showMessageDialog(null, "Deve pelo menos existir um estado final.");
				}
			}

		} while (numStates <= 0);

		return numStates;
	}

//gerar todos os estados possiveis
	public static List<SortedSet<Comparable>> genAllStates(ArrayList<ArrayList<String>> automaton, int numStates) {

		ArrayList<String> listStates = new ArrayList<>();

		for (int x = 0; x < automaton.size(); x++) {
			ArrayList<String> parcialList = automaton.get(x);

			if (!listStates.contains(parcialList.get(0))) {
				listStates.add(parcialList.get(0));
			}

			for (int y = 0; y < listStates.size(); y++) {
				if (!listStates.get(y).equals(parcialList.get(2)) && !listStates.contains(parcialList.get(2))) {
					listStates.add(parcialList.get(2));
				}
			}
		}

		List<SortedSet<Comparable>> listAllStates = new ArrayList<SortedSet<Comparable>>();

		for (String nstatus : listStates) {
			listAllStates.add(new TreeSet<Comparable>(Arrays.asList(nstatus)));
		}

		for (int nivel = 1; nivel < listStates.size(); nivel++) {
			List<SortedSet<Comparable>> statusAntes = new ArrayList<SortedSet<Comparable>>(listAllStates);
			for (Set<Comparable> antes : statusAntes) {
				SortedSet<Comparable> novo = new TreeSet<Comparable>(antes);
				novo.add(listStates.get(nivel));
				if (!listAllStates.contains(novo)) {
					listAllStates.add(novo);
				}
			}
		}

		Collections.sort(listAllStates, new Comparator<SortedSet<Comparable>>() {

			@Override
			public int compare(SortedSet<Comparable> o1, SortedSet<Comparable> o2) {
				int sizeComp = o1.size() - o2.size();
				if (sizeComp == 0) {
					Iterator<Comparable> o1iIterator = o1.iterator();
					Iterator<Comparable> o2iIterator = o2.iterator();
					while (sizeComp == 0 && o1iIterator.hasNext()) {
						sizeComp = o1iIterator.next().compareTo(o2iIterator.next());
					}
				}
				return sizeComp;
			}
		});

		return listAllStates;

	}

	// arrumando bagunça que codigo externo causou
	public static ArrayList<ArrayList<String>> sortedSetTrans(List<SortedSet<Comparable>> listAllStates) {
		ArrayList<ArrayList<String>> allStates = new ArrayList<>();

		for (int x = 0; x < listAllStates.size(); x++) {
			SortedSet<Comparable> las = listAllStates.get(x);
			ArrayList<String> arrayInterno = new ArrayList<>();
			Object[] str = las.toArray();
			for (int i = 0; i < las.size(); i++) {
				String obj = str[i].toString();
				arrayInterno.add(obj);
			}
			allStates.add(arrayInterno);
		}

		return allStates;
	}

	// criando novos estado conforme o index
	public static ArrayList<String> organizeNewStates(ArrayList<ArrayList<String>> allStates, int numStates) {
		ArrayList<String> newAllStates = new ArrayList<>();

		int cont = numStates;

		for (int y = 0; y < allStates.size(); y++) {
			if (allStates.get(y).size() == 1) {
				ArrayList<String> valor = allStates.get(y);
				newAllStates.add(y, valor.get(0));
			} else if (allStates.get(y).size() > 1) {
				newAllStates.add(Integer.toString(cont));
				cont++;
			}
		}

		return newAllStates;

	}

	public static ArrayList<ArrayList<String>> tableGen(ArrayList<ArrayList<String>> allStates, ArrayList<String> newAllStates,
			ArrayList<ArrayList<String>> automaton, ArrayList<String> alphabet, int numStates) {

		List<String> newStates = new ArrayList<>();

		int verf = 0;

		int contador = 0;

		String stateAnalized = newAllStates.get(0);

		ArrayList<ArrayList<ArrayList<String>>> listPartialTrans = new ArrayList<>();

		for (int lenSt = 0; lenSt < newAllStates.size(); lenSt++) {

			ArrayList<String> partialFinalState = new ArrayList<>();
			ArrayList<String> partialNewState = new ArrayList<>();
			ArrayList<String> partialTrans = new ArrayList<>();
			ArrayList<String> listState = new ArrayList<>();

			if (verf == 0) {

				for (int at = 0; at < automaton.size(); at++) {
					ArrayList<String> partialAutomaton = automaton.get(at);
					for (String src : newAllStates) {
						if (src.equals(stateAnalized)) {
							listState = allStates.get(Integer.parseInt(src));

						}
						if (listState.size() > 1) {
							for (int ls = 0; ls < listState.size(); ls++) {
								if (partialAutomaton.get(0).equals(listState.get(ls))) {
									for (String alph : alphabet) {
										if (partialAutomaton.get(1).equals(alph)) {
											if (!partialFinalState.contains(partialAutomaton.get(2))) {
												partialFinalState.add(partialAutomaton.get(2));
											}
											if (!partialTrans.contains(partialAutomaton.get(1))) {
												partialTrans.add(partialAutomaton.get(1));
											}
											ArrayList<ArrayList<String>> partialConn = new ArrayList<>();
											partialConn.add(partialTrans);
											partialConn.add(partialFinalState);
											if (listPartialTrans.isEmpty()) {
												listPartialTrans.add(partialConn);
											}
											verf = 1;
										}
									}

									if (partialTrans.isEmpty()) {
										break;
									}
								}
							}
						} else {
							for (String alph : alphabet) {
								if (partialAutomaton.get(0).equals(stateAnalized)
										&& partialAutomaton.get(1).equals(alph)) {
									if (!partialFinalState.contains(partialAutomaton.get(2))) {
										partialFinalState.add(partialAutomaton.get(2));
									}
									if (!partialTrans.contains(partialAutomaton.get(1))) {
										partialTrans.add(partialAutomaton.get(1));
									}
									ArrayList<ArrayList<String>> partialConn = new ArrayList<>();
									partialConn.add(partialTrans);
									partialConn.add(partialFinalState);
									if (listPartialTrans.isEmpty()) {
										listPartialTrans.add(partialConn);
									}

									verf = 1;
								}

								if (partialTrans.isEmpty()) {
									break;
								}
							}
						}
					}
				}

				if (verf == 1) {
					String p0 = "";
					String p1 = "";
					String p2 = "";

					if (listPartialTrans.get(0).get(0).size() > 1 && !partialFinalState.isEmpty()) {
						for (int lpt = 0; lpt < listPartialTrans.get(0).get(0).size(); lpt++) {
							partialNewState.clear();
							p0 = stateAnalized;
							p1 = listPartialTrans.get(0).get(0).get(lpt);
							if (listPartialTrans.get(0).get(1).size() > 1) {
								for (int z = 0; z < allStates.size(); z++) {
									if (allStates.get(z).size() > 1) {
										if (allStates.get(z).equals(listPartialTrans.get(0).get(1))) {
											p2 = Integer.toString(z);
										}
									}
								}
							} else {
								p2 = listPartialTrans.get(0).get(1).get(0);
							}

							partialNewState.add(0, p0);
							partialNewState.add(1, p1);
							partialNewState.add(2, p2);

							if (!partialNewState.get(0).isEmpty()) {
								System.out.println("partial new state: " + partialNewState);
								newStates.add(partialNewState.toString());
								System.out.println("new State: " + newStates);
								verf = 2;
								contador++;
							}
						}
					} else if (listPartialTrans.get(0).get(0).size() == 1 && !partialFinalState.isEmpty()) {
						p0 = stateAnalized;
						p1 = listPartialTrans.get(0).get(0).get(0);
						if (listPartialTrans.get(0).get(1).size() > 1) {
							for (int z = 0; z < allStates.size(); z++) {
								if (allStates.get(z).size() > 1) {
									if (allStates.get(z).equals(listPartialTrans.get(0).get(1))) {
										p2 = Integer.toString(z);
									}
								}
							}
						} else {
							p2 = listPartialTrans.get(0).get(1).get(0);
						}

						partialNewState.add(0, p0);
						partialNewState.add(1, p1);
						partialNewState.add(2, p2);

						if (!partialNewState.get(0).isEmpty()) {
							System.out.println("partial new state: " + partialNewState);
							newStates.add(partialNewState.toString());
							System.out.println("new State: " + newStates);
							verf = 2;
							contador++;
						}
					}
				}

				if (verf == 2) {
					stateAnalized = partialNewState.get(2);
					listPartialTrans.clear();
					partialFinalState.clear();
					partialNewState.clear();
					verf = 0;
				}
			}
		}

		System.out.println("/////////////////////////////////");

		for (ArrayList<String> as : allStates) {
			System.out.println(as);
		}

		System.out.println("/////////////////////////////////");

		ArrayList<ArrayList<String>> finalNewStates = new ArrayList<>();

		for (String ns : newStates) {
			ArrayList<String> charList = new ArrayList<>();
			char[] chr = ns.toCharArray();
			for (char c : chr) {
				if (c == ' ' || c == ',' || c == '[' || c == ']') {
					// não faz nada
				} else {
					charList.add(String.valueOf(c));
				}
			}
			finalNewStates.add(charList);
		}

//		for (ArrayList<String> a : asd) {
//			System.out.println("a: " + a);
//			for (String b : a) {
//				System.out.println(b);
//			}
//		}

		return finalNewStates;
	}

}
