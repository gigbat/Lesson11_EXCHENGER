import java.util.concurrent.Exchanger;

public class ExchengerImpl {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Mike(exchanger);
        new Anketa(exchanger);
    }

    static class Mike extends Thread {
        Exchanger<String> exchanger;
        @Override
        public void run() {
            try {
                // input data to class of exchenger.
                exchanger.exchange("Hi, my name is Mike");
                // just sleeping
                sleep(3000);
                // input data to class of exchenger.
                exchanger.exchange("I`m 20 years old");
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        Mike(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }
    }

    static class Anketa extends Thread {
        Exchanger<String> exchanger;

        @Override
        public void run() {
            try {
                // get info from simple exchenger`s. For example stroke 16,18
                String name = exchanger.exchange(null);
                String age = exchanger.exchange(null);

                // out data of exchenger
                System.out.println(name);
                System.out.println(age);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        Anketa(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }
    }
}