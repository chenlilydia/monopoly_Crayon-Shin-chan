package context;

/**
 * 
 * Game Constant
 *
 */
public class GameState {

	// 停留在建筑返回状态

	public  final static int HOSPITAL_EVENT = 1;

	public  final static int HUOSE_EVENT = 2;

	public  final static int LOTTERY_EVENT = 3;

	public  final static int NEWS_EVENT = 4;

	public  final static int ORIGIN_EVENT = 5;

	public  final static int PARK_EVENT = 6;

	public  final static int POINT_EVENT = 7;

	public  final static int PRISON_EVENT = 8;

	public  final static int SHOP_EVENT = 9;

	// 路过建筑返回状态
	public final static int ORIGIN_PASS_EVENT = 1;

	// 使用卡片返回状态
	public  final static int CARD_ADDLEVEL = 1;

	public  final static int CARD_AVERAGERPOOR = 2;

	public  final static int CARD_CONTROLDICE = 3;

	public  final static int CARD_HAVE = 4;

	public  final static int CARD_TORTOISE = 5;

	public  final static int CARD_GOODLUCK = 6;

	// 卡片作用效果返回状态
	public final static int CARD_BUFF_STOP = 1;

	public final static int CARD_BUFF_TORTOISE = 2;

}
