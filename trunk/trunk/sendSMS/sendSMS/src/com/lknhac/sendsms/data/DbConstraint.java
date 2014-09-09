package com.lknhac.sendsms.data;

public class DbConstraint {
	public static final String DATABASE_NAME = "sendsms.db"; 
    public static final int DATABASE_VERSION = 1;
        	
	// Fields of EXCEPTIONS table
	public static final String EXCEPTIONS = "phonelist";
	public static final String STT = "stt";
	public static final String NAME = "name";
	public static final String PHONES = "phones";
	public static final String CREATE_TABLE_EXCEPTION_VERSION = "CREATE VIRTUAL TABLE "
			+ EXCEPTIONS + " USING FTS3(" +
			STT + " TEXT, " +
			NAME + " TEXT, " + 			
			PHONES + " TEXT)";
	
//	// Fields of SUMMARY table
//	public static final String SUMMARY = "summary";
//	public static final String DATE = "date";
//	public static final String TYPE = "type";
//	public static final String NUMBER_CALL = "number_call";
//	public static final String CREATE_TABLE_SUMMARY_VERSION = "CREATE VIRTUAL TABLE "
//			+ SUMMARY + " USING FTS3(" +
//			USERNAME + " TEXT, " +
//			NAME + " TEXT, " +
//			DATE + " TEXT, " +
//			TYPE + " INTEGER, " +
//			NUMBER_CALL + " INTEGER, " +
//			PHONES + " TEXT)";
	
//	// Fields of USER table
//	public static final String USER = "user";
////	public static final String USERNAME = "username";
//	public static final String PURCHASED = "purchased";
//	public static final String DISABLE_WIFI = "disableWifi";
//	public static final String DISABLE_GPS = "disableGps";
//	public static final String DISABLE_CELL_SERVICE = "disable";
//	
//	public static final String IS_AUTO_RESPONSE_TEXT = "isResponseText";
//	public static final String IS_USE_DEFAULT_AUTO_RESPONSE_TEXT = "isDefaultResponseText";
//	public static final String AUTO_RESPONSE_TEXT = "responseText";
//	
//	public static final String IS_AUTO_RESPONSE_VOICE = "isResponseVoice";
//	public static final String IS_USE_DEFAULT_AUTO_RESPONSE_VOICE = "isDefaultResponseVoice";
//	public static final String AUTO_RESPONSE_VOICE = "responseVoice";
//	
//	public static final String TOTAL_TIME_OFF = "totalTime";
//	
//	public static final String CREATE_TABLE_USER_VERSION = "CREATE VIRTUAL TABLE "
//			+ USER + " USING FTS3(" +
//			USERNAME + " TEXT, " +
//			NAME + " TEXT, " +
//			PURCHASED + " INTEGER, " +
//			DISABLE_WIFI + " INTEGER, " +
//			DISABLE_GPS + " INTEGER, " +
//			DISABLE_CELL_SERVICE + " INTEGER, " +
//			IS_AUTO_RESPONSE_TEXT + " INTEGER, " +
//			IS_USE_DEFAULT_AUTO_RESPONSE_TEXT + " INTEGER, " +
//			AUTO_RESPONSE_TEXT + " TEXT, " +
//			IS_AUTO_RESPONSE_VOICE + " INTEGER, " +
//			IS_USE_DEFAULT_AUTO_RESPONSE_VOICE + " INTEGER, " +
//			AUTO_RESPONSE_VOICE + " TEXT, " +
//			TOTAL_TIME_OFF + " INTEGER)";
}
