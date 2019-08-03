package sunit.threading;

public class Async<T> {
	private Object lockObject = new Object();
	private int timeout;
	private T value;
	
	public Async(int timeout) {
		this.timeout = timeout;
	}
	
	public synchronized T get(Task task) throws Exception {
		synchronized (lockObject) {
			task.run();
			lockObject.wait(timeout);
			return value;
		}
	}
	
	public void set(T value) {
		synchronized (lockObject) {
			this.value = value;
			lockObject.notify();
		}
	}
}
