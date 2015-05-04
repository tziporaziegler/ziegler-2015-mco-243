package ziegler.philosophers;

public class DiningPhilosophers {

	public static void main(String[] args) {
		Fork forks[] = new Fork[5];
		for (int i = 0; i < forks.length; i++) {
			forks[i] = new Fork(i);
		}

		Philosopher[] philos = new Philosopher[5];
		philos[0] = new Philosopher(forks[0], forks[1], "A");
		philos[1] = new Philosopher(forks[1], forks[2], "B");
		philos[2] = new Philosopher(forks[2], forks[3], "C");
		philos[3] = new Philosopher(forks[3], forks[4], "D");
		philos[4] = new Philosopher(forks[4], forks[0], "E");

		for(Philosopher philo: philos){
			philo.start();
		}
	}
}