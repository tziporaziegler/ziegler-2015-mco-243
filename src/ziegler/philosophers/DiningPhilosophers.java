package ziegler.philosophers;

public class DiningPhilosophers {

	public static void main(String[] args) {
		Fork forks[] = new Fork[5];
		for (int i = 0; i < forks.length; i++) {
			forks[i] = new Fork(String.valueOf(i));
		}

		Philosopher[] philos = new Philosopher[5];
		Philosopher philoA = new Philosopher(forks[0], forks[1], "A");
		Philosopher philoB = new Philosopher(forks[1], forks[2], "B");
		Philosopher philoC = new Philosopher(forks[2], forks[3], "C");
		Philosopher philoD = new Philosopher(forks[3], forks[4], "D");
		Philosopher philoE = new Philosopher(forks[4], forks[0], "E");

		//philoA.setNeighbors(philoE, philoB);
		//philoB.setNeighbors(philoA, philoC);
		//philoC.setNeighbors(philoB, philoD);
		//philoD.setNeighbors(philoC, philoE);
		//philoE.setNeighbors(philoD, philoA);

		for(Philosopher philo: philos){
			philo.start();
		}
	}
}