package model;

/**
 * 
 * ��Ϸ������
 *
 */
public class Tick {
	/**
	 * ���ص�ǰ��tickֵ
	 */
	protected long nowTick;
	
	/**
	 * ��ʼ�¼���tickֵ
	 */
	protected long startTick;
	/**
	 * �¼�����ʱ�� s * 30;
	 */
	protected long lastTime;
	/**
	 * ��һ��tickֵ
	 */
	protected long nextTick;
	
	public long getLastTime() {
		return lastTime;
	}
	
	public long getNowTick() {
		return nowTick;
	}

	public long getStartTick() {
		return startTick;
	}

	public long getNextTick() {
		return nextTick;
	}
	

	public void setNowTick(long nowTick) {
		this.nowTick = nowTick;
	}

	public void setStartTick(long startTick) {
		this.startTick = startTick;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public void setNextTick(long nextTick) {
		this.nextTick = nextTick;
	}

}
