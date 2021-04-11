package client;

import java.util.ArrayList;

import src.*;

public class Client {

	public static void main(String[] args) {

		Deterministico deterministico = new Deterministico();
		NaoDeterministico naoDeterministico = new NaoDeterministico();
		ArrayList<String> alphabet = new ArrayList<>();

		alphabet = deterministico.alphabetDef();
		int numStates = naoDeterministico.statesCount();

//		deterministico.verfWord(deterministico.wordDef(), deterministico.initState(), 
//				deterministico.finalState(), deterministico.automatonDef(deterministico.transCount(), alphabet));

//		System.out.println(naoDeterministico.genAllStates(
//				deterministico.automatonDef(deterministico.transCount(), alphabet), naoDeterministico.statesCount()));
		
		naoDeterministico.organizeNewStates(naoDeterministico.genAllStates(
				deterministico.automatonDef(deterministico.transCount(), alphabet), numStates), numStates);
	}

}
