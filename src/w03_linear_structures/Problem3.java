package w03_linear_structures;

public class Problem3 {
    public static void main(String[] args) {
        QueueApplication.eventSimulation();
    }
}

class QueueApplication {
    static class Event {
        int arrival;
        int duration;

        public Event(int a, int d) {
            this.arrival = a;
            this.duration = d;
        }
    }

    static void eventSimulation() {
        ArrayQueue<Event> events = new ArrayQueue<>();
        events.enQueue(new Event(0, 5));
        events.enQueue(new Event(3, 3));
        events.enQueue(new Event(4, 4));
        events.enQueue(new Event(100, 4));

        int n = events.size();

        int nextAvailableTime = 0;
        int totalWaitingTime = 0;
        int maxWaitingTime = 0;

        while (!events.isEmpty()) {
            Event evt = events.peekFront();
            events.deQueue();
            nextAvailableTime = Math.max(nextAvailableTime, evt.arrival);
            int waitingTime = nextAvailableTime - evt.arrival;
            maxWaitingTime = Math.max(maxWaitingTime, waitingTime);
            totalWaitingTime += waitingTime;
            nextAvailableTime = nextAvailableTime + evt.duration;
        }

        System.out.printf("Max waiting time %d, total waiting time %d and average waiting time %.2f\n", maxWaitingTime, totalWaitingTime, 1.0 * totalWaitingTime / n);
    }
}
