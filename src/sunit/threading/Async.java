package sunit.threading;

public class Async<T> {
	private Object lock = new Object();
	private int timeout;
	private T value;
	
	public Async(int timeout) {
		this.timeout = timeout;
	}
	
	public synchronized T get(Task task) throws Exception {
		task.run();
		
		synchronized (lock) {
			lock.wait(timeout);
		}
		return value;
	}
	
	public void set(T value) {
		this.value = value;
		
		synchronized (lock) {
			lock.notify();
		}
	}
}
