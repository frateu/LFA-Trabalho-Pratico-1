package src;

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
import src.Deterministico;

public class NaoDeterministico {

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
	
	public static ArrayList<String> organizeNewStates(List<SortedSet<Comparable>> listAllStates, int numStates){
		ArrayList<ArrayList<String>> allStates = new ArrayList<>();
		ArrayList<String> newAllStates = new ArrayList<>();
		
		// transformar a sortedset em array list
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
		
		int cont = numStates;
		
		for (int y = 0; y < allStates.size(); y++) {
			if (allStates.get(y).size() == 1) {
				ArrayList<String> valor = allStates.get(y);
				newAllStates.add(y, valor.get(0) );
				}else if (allStates.get(y).size() > 1) {
					newAllStates.add(Integer.toString(cont));
					cont++;
				}
			}
		
		System.out.println(newAllStates);

		return newAllStates;

	}
	
}
