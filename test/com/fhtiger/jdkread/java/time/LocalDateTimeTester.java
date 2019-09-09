package com.fhtiger.jdkread.java.time;

import com.fhtiger.jdkread.TestStarter;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * LocaleDateTester
 *
 * @author LFH
 * @since 2019年09月08日 17:12
 */
public class LocalDateTimeTester extends TestStarter {

	/**
	 * 先看几个小知识点
	 */
	@Test
	public void preTest(){

		outr("格林尼治:",Instant.EPOCH);
		/*格林尼治起始时间:1970-01-01T00:00:00Z*/

		outfr("1秒 = %.0e 纳秒;",
				(double)LocalTime.ofSecondOfDay(1).toNanoOfDay());
		/*1秒 = 1e+09 纳秒;即:1e9 纳秒*/

		outfr("1毫秒 = %.0e 纳秒;",
				(double)LocalTime.ofSecondOfDay(1).toNanoOfDay()/1000);
		/*1毫秒 = 1e+06 纳秒;即:1e6 纳秒*/

	}

	/**
	 * 获取当前时间
	 */
	@Test
	public void getNowTimeTest(){

		//1.获取当前时间
		outr(LocalDateTime.now());
		//=>2019-09-08T19:50:22.808

		//2.获取当前日期
		outr(LocalDate.now());
		//=> 2019-09-08

		//3.获取当前时间
		outr(LocalTime.now());
		//=> 22:22:41.956

		//4.获取当前时间:使用系统默认时区,等同于1.
		outr(LocalDateTime.now(ZoneId.systemDefault()));

		//5.获取当前时间:使用系统分区对应的时钟,等同于1.、4.
		outr(LocalDateTime.now(Clock.systemDefaultZone()));

		//6.当前系统默认时区
		outr(ZoneId.systemDefault());
		//=> Asia/Shanghai

		//7.当前系统默认时钟
		outr(Clock.systemDefaultZone());
		//=> SystemClock[Asia/Shanghai]

		outr( Clock.systemUTC());
		//=> SystemClock[Z]

		//8.1可用的时区
		Set<String> zones = ZoneId.getAvailableZoneIds();

		outr("zones:",zones.toString().replaceAll(",",",\t"));
		//=> zones: [Asia/Aden,	 America/Cuiaba,	 Etc/GMT+9,	 Etc/GMT+8,	 Africa/Nairobi,
		// America/Marigot,	 Asia/Aqtau,	 Pacific/Kwajalein,	 America/El_Salvador,	 Asia/Pontianak,	 Africa/Cairo,	 Pacific/Pago_Pago,	 Africa/Mbabane,	 Asia/Kuching,	 Pacific/Honolulu,	 Pacific/Rarotonga,	 America/Guatemala,	 Australia/Hobart,	 Europe/London,	 America/Belize,	 America/Panama,	 Asia/Chungking,	 America/Managua,	 America/Indiana/Petersburg,	 Asia/Yerevan,	 Europe/Brussels,	 GMT,	 Europe/Warsaw,	 America/Chicago,	 Asia/Kashgar,	 Chile/Continental,	 Pacific/Yap,	 CET,	 Etc/GMT-1,	 Etc/GMT-0,	 Europe/Jersey,	 America/Tegucigalpa,	 Etc/GMT-5,	 Europe/Istanbul,	 America/Eirunepe,	 Etc/GMT-4,	 America/Miquelon,	 Etc/GMT-3,	 Europe/Luxembourg,	 Etc/GMT-2,	 Etc/GMT-9,	 America/Argentina/Catamarca,	 Etc/GMT-8,	 Etc/GMT-7,	 Etc/GMT-6,	 Europe/Zaporozhye,	 Canada/Yukon,	 Canada/Atlantic,	 Atlantic/St_Helena,	 Australia/Tasmania,	 Libya,	 Europe/Guernsey,	 America/Grand_Turk,	 US/Pacific-New,	 Asia/Samarkand,	 America/Argentina/Cordoba,	 Asia/Phnom_Penh,	 Africa/Kigali,	 Asia/Almaty,	 US/Alaska,	 Asia/Dubai,	 Europe/Isle_of_Man,	 America/Araguaina,	 Cuba,	 Asia/Novosibirsk,	 America/Argentina/Salta,	 Etc/GMT+3,	 Africa/Tunis,	 Etc/GMT+2,	 Etc/GMT+1,	 Pacific/Fakaofo,	 Africa/Tripoli,	 Etc/GMT+0,	 Israel,	 Africa/Banjul,	 Etc/GMT+7,	 Indian/Comoro,	 Etc/GMT+6,	 Etc/GMT+5,	 Etc/GMT+4,	 Pacific/Port_Moresby,	 US/Arizona,	 Antarctica/Syowa,	 Indian/Reunion,	 Pacific/Palau,	 Europe/Kaliningrad,	 America/Montevideo,	 Africa/Windhoek,	 Asia/Karachi,	 Africa/Mogadishu,	 Australia/Perth,	 Brazil/East,	 Etc/GMT,	 Asia/Chita,	 Pacific/Easter,	 Antarctica/Davis,	 Antarctica/McMurdo,	 Asia/Macao,	 America/Manaus,	 Africa/Freetown,	 Europe/Bucharest,	 America/Argentina/Mendoza,	 Asia/Macau,	 Europe/Malta,	 Mexico/BajaSur,	 Pacific/Tahiti,	 Africa/Asmera,	 Europe/Busingen,	 America/Argentina/Rio_Gallegos,	 Africa/Malabo,	 Europe/Skopje,	 America/Catamarca,	 America/Godthab,	 Europe/Sarajevo,	 Australia/ACT,	 GB-Eire,	 Africa/Lagos,	 America/Cordoba,	 Europe/Rome,	 Asia/Dacca,	 Indian/Mauritius,	 Pacific/Samoa,	 America/Regina,	 America/Fort_Wayne,	 America/Dawson_Creek,	 Africa/Algiers,	 Europe/Mariehamn,	 America/St_Johns,	 America/St_Thomas,	 Europe/Zurich,	 America/Anguilla,	 Asia/Dili,	 America/Denver,	 Africa/Bamako,	 GB,	 Mexico/General,	 Pacific/Wallis,	 Europe/Gibraltar,	 Africa/Conakry,	 Africa/Lubumbashi,	 Asia/Istanbul,	 America/Havana,	 NZ-CHAT,	 Asia/Choibalsan,	 America/Porto_Acre,	 Asia/Omsk,	 Europe/Vaduz,	 US/Michigan,	 Asia/Dhaka,	 America/Barbados,	 Europe/Tiraspol,	 Atlantic/Cape_Verde,	 Asia/Yekaterinburg,	 America/Louisville,	 Pacific/Johnston,	 Pacific/Chatham,	 Europe/Ljubljana,	 America/Sao_Paulo,	 Asia/Jayapura,	 America/Curacao,	 Asia/Dushanbe,	 America/Guyana,	 America/Guayaquil,	 America/Martinique,	 Portugal,	 Europe/Berlin,	 Europe/Moscow,	 Europe/Chisinau,	 America/Puerto_Rico,	 America/Rankin_Inlet,	 Pacific/Ponape,	 Europe/Stockholm,	 Europe/Budapest,	 America/Argentina/Jujuy,	 Australia/Eucla,	 Asia/Shanghai,	 Universal,	 Europe/Zagreb,	 America/Port_of_Spain,	 Europe/Helsinki,	 Asia/Beirut,	 Asia/Tel_Aviv,	 Pacific/Bougainville,	 US/Central,	 Africa/Sao_Tome,	 Indian/Chagos,	 America/Cayenne,	 Asia/Yakutsk,	 Pacific/Galapagos,	 Australia/North,	 Europe/Paris,	 Africa/Ndjamena,	 Pacific/Fiji,	 America/Rainy_River,	 Indian/Maldives,	 Australia/Yancowinna,	 SystemV/AST4,	 Asia/Oral,	 America/Yellowknife,	 Pacific/Enderbury,	 America/Juneau,	 Australia/Victoria,	 America/Indiana/Vevay,	 Asia/Tashkent,	 Asia/Jakarta,	 Africa/Ceuta,	 America/Recife,	 America/Buenos_Aires,	 America/Noronha,	 America/Swift_Current,	 Australia/Adelaide,	 America/Metlakatla,	 Africa/Djibouti,	 America/Paramaribo,	 Europe/Simferopol,	 Europe/Sofia,	 Africa/Nouakchott,	 Europe/Prague,	 America/Indiana/Vincennes,	 Antarctica/Mawson,	 America/Kralendijk,	 Antarctica/Troll,	 Europe/Samara,	 Indian/Christmas,	 America/Antigua,	 Pacific/Gambier,	 America/Indianapolis,	 America/Inuvik,	 America/Iqaluit,	 Pacific/Funafuti,	 UTC,	 Antarctica/Macquarie,	 Canada/Pacific,	 America/Moncton,	 Africa/Gaborone,	 Pacific/Chuuk,	 Asia/Pyongyang,	 America/St_Vincent,	 Asia/Gaza,	 Etc/Universal,	 PST8PDT,	 Atlantic/Faeroe,	 Asia/Qyzylorda,	 Canada/Newfoundland,	 America/Kentucky/Louisville,	 America/Yakutat,	 Asia/Ho_Chi_Minh,	 Antarctica/Casey,	 Europe/Copenhagen,	 Africa/Asmara,	 Atlantic/Azores,	 Europe/Vienna,	 ROK,	 Pacific/Pitcairn,	 America/Mazatlan,	 Australia/Queensland,	 Pacific/Nauru,	 Europe/Tirane,	 Asia/Kolkata,	 SystemV/MST7,	 Australia/Canberra,	 MET,	 Australia/Broken_Hill,	 Europe/Riga,	 America/Dominica,	 Africa/Abidjan,	 America/Mendoza,	 America/Santarem,	 Kwajalein,	 America/Asuncion,	 Asia/Ulan_Bator,	 NZ,	 America/Boise,	 Australia/Currie,	 EST5EDT,	 Pacific/Guam,	 Pacific/Wake,	 Atlantic/Bermuda,	 America/Costa_Rica,	 America/Dawson,	 Asia/Chongqing,	 Eire,	 Europe/Amsterdam,	 America/Indiana/Knox,	 America/North_Dakota/Beulah,	 Africa/Accra,	 Atlantic/Faroe,	 Mexico/BajaNorte,	 America/Maceio,	 Etc/UCT,	 Pacific/Apia,	 GMT0,	 America/Atka,	 Pacific/Niue,	 Canada/East-Saskatchewan,	 Australia/Lord_Howe,	 Europe/Dublin,	 Pacific/Truk,	 MST7MDT,	 America/Monterrey,	 America/Nassau,	 America/Jamaica,	 Asia/Bishkek,	 America/Atikokan,	 Atlantic/Stanley,	 Australia/NSW,	 US/Hawaii,	 SystemV/CST6,	 Indian/Mahe,	 Asia/Aqtobe,	 America/Sitka,	 Asia/Vladivostok,	 Africa/Libreville,	 Africa/Maputo,	 Zulu,	 America/Kentucky/Monticello,	 Africa/El_Aaiun,	 Africa/Ouagadougou,	 America/Coral_Harbour,	 Pacific/Marquesas,	 Brazil/West,	 America/Aruba,	 America/North_Dakota/Center,	 America/Cayman,	 Asia/Ulaanbaatar,	 Asia/Baghdad,	 Europe/San_Marino,	 America/Indiana/Tell_City,	 America/Tijuana,	 Pacific/Saipan,	 SystemV/YST9,	 Africa/Douala,	 America/Chihuahua,	 America/Ojinaga,	 Asia/Hovd,	 America/Anchorage,	 Chile/EasterIsland,	 America/Halifax,	 Antarctica/Rothera,	 America/Indiana/Indianapolis,	 US/Mountain,	 Asia/Damascus,	 America/Argentina/San_Luis,	 America/Santiago,	 Asia/Baku,	 America/Argentina/Ushuaia,	 Atlantic/Reykjavik,	 Africa/Brazzaville,	 Africa/Porto-Novo,	 America/La_Paz,	 Antarctica/DumontDUrville,	 Asia/Taipei,	 Antarctica/South_Pole,	 Asia/Manila,	 Asia/Bangkok,	 Africa/Dar_es_Salaam,	 Poland,	 Atlantic/Madeira,	 Antarctica/Palmer,	 America/Thunder_Bay,	 Africa/Addis_Ababa,	 Europe/Uzhgorod,	 Brazil/DeNoronha,	 Asia/Ashkhabad,	 Etc/Zulu,	 America/Indiana/Marengo,	 America/Creston,	 America/Mexico_City,	 Antarctica/Vostok,	 Asia/Jerusalem,	 Europe/Andorra,	 US/Samoa,	 PRC,	 Asia/Vientiane,	 Pacific/Kiritimati,	 America/Matamoros,	 America/Blanc-Sablon,	 Asia/Riyadh,	 Iceland,	 Pacific/Pohnpei,	 Asia/Ujung_Pandang,	 Atlantic/South_Georgia,	 Europe/Lisbon,	 Asia/Harbin,	 Europe/Oslo,	 Asia/Novokuznetsk,	 CST6CDT,	 Atlantic/Canary,	 America/Knox_IN,	 Asia/Kuwait,	 SystemV/HST10,	 Pacific/Efate,	 Africa/Lome,	 America/Bogota,	 America/Menominee,	 America/Adak,	 Pacific/Norfolk,	 America/Resolute,	 Pacific/Tarawa,	 Africa/Kampala,	 Asia/Krasnoyarsk,	 Greenwich,	 SystemV/EST5,	 America/Edmonton,	 Europe/Podgorica,	 Australia/South,	 Canada/Central,	 Africa/Bujumbura,	 America/Santo_Domingo,	 US/Eastern,	 Europe/Minsk,	 Pacific/Auckland,	 Africa/Casablanca,	 America/Glace_Bay,	 Canada/Eastern,	 Asia/Qatar,	 Europe/Kiev,	 Singapore,	 Asia/Magadan,	 SystemV/PST8,	 America/Port-au-Prince,	 Europe/Belfast,	 America/St_Barthelemy,	 Asia/Ashgabat,	 Africa/Luanda,	 America/Nipigon,	 Atlantic/Jan_Mayen,	 Brazil/Acre,	 Asia/Muscat,	 Asia/Bahrain,	 Europe/Vilnius,	 America/Fortaleza,	 Etc/GMT0,	 US/East-Indiana,	 America/Hermosillo,	 America/Cancun,	 Africa/Maseru,	 Pacific/Kosrae,	 Africa/Kinshasa,	 Asia/Kathmandu,	 Asia/Seoul,	 Australia/Sydney,	 America/Lima,	 Australia/LHI,	 America/St_Lucia,	 Europe/Madrid,	 America/Bahia_Banderas,	 America/Montserrat,	 Asia/Brunei,	 America/Santa_Isabel,	 Canada/Mountain,	 America/Cambridge_Bay,	 Asia/Colombo,	 Australia/West,	 Indian/Antananarivo,	 Australia/Brisbane,	 Indian/Mayotte,	 US/Indiana-Starke,	 Asia/Urumqi,	 US/Aleutian,	 Europe/Volgograd,	 America/Lower_Princes,	 America/Vancouver,	 Africa/Blantyre,	 America/Rio_Branco,	 America/Danmarkshavn,	 America/Detroit,	 America/Thule,	 Africa/Lusaka,	 Asia/Hong_Kong,	 Iran,	 America/Argentina/La_Rioja,	 Africa/Dakar,	 SystemV/CST6CDT,	 America/Tortola,	 America/Porto_Velho,	 Asia/Sakhalin,	 Etc/GMT+10,	 America/Scoresbysund,	 Asia/Kamchatka,	 Asia/Thimbu,	 Africa/Harare,	 Etc/GMT+12,	 Etc/GMT+11,	 Navajo,	 America/Nome,	 Europe/Tallinn,	 Turkey,	 Africa/Khartoum,	 Africa/Johannesburg,	 Africa/Bangui,	 Europe/Belgrade,	 Jamaica,	 Africa/Bissau,	 Asia/Tehran,	 WET,	 Africa/Juba,	 America/Campo_Grande,	 America/Belem,	 Etc/Greenwich,	 Asia/Saigon,	 America/Ensenada,	 Pacific/Midway,	 America/Jujuy,	 Africa/Timbuktu,	 America/Bahia,	 America/Goose_Bay,	 America/Virgin,	 America/Pangnirtung,	 Asia/Katmandu,	 America/Phoenix,	 Africa/Niamey,	 America/Whitehorse,	 Pacific/Noumea,	 Asia/Tbilisi,	 America/Montreal,	 Asia/Makassar,	 America/Argentina/San_Juan,	 Hongkong,	 UCT,	 Asia/Nicosia,	 America/Indiana/Winamac,	 SystemV/MST7MDT,	 America/Argentina/ComodRivadavia,	 America/Boa_Vista,	 America/Grenada,	 Australia/Darwin,	 Asia/Khandyga,	 Asia/Kuala_Lumpur,	 Asia/Thimphu,	 Asia/Rangoon,	 Europe/Bratislava,	 Asia/Calcutta,	 America/Argentina/Tucuman,	 Asia/Kabul,	 Indian/Cocos,	 Japan,	 Pacific/Tongatapu,	 America/New_York,	 Etc/GMT-12,	 Etc/GMT-11,	 Etc/GMT-10,	 SystemV/YST9YDT,	 Etc/GMT-14,	 Etc/GMT-13,	 W-SU,	 America/Merida,	 EET,	 America/Rosario,	 Canada/Saskatchewan,	 America/St_Kitts,	 Arctic/Longyearbyen,	 America/Caracas,	 America/Guadeloupe,	 Asia/Hebron,	 Indian/Kerguelen,	 SystemV/PST8PDT,	 Africa/Monrovia,	 Asia/Ust-Nera,	 Egypt,	 Asia/Srednekolymsk,	 America/North_Dakota/New_Salem,	 Asia/Anadyr,	 Australia/Melbourne,	 Asia/Irkutsk,	 America/Shiprock,	 America/Winnipeg,	 Europe/Vatican,	 Asia/Amman,	 Etc/UTC,	 SystemV/AST4ADT,	 Asia/Tokyo,	 America/Toronto,	 Asia/Singapore,	 Australia/Lindeman,	 America/Los_Angeles,	 SystemV/EST5EDT,	 Pacific/Majuro,	 America/Argentina/Buenos_Aires,	 Europe/Nicosia,	 Pacific/Guadalcanal,	 Europe/Athens,	 US/Pacific,	 Europe/Monaco]
		outr("---------");

		//8.2有可用简称的时区对照.
		Map<String, String> zonesMap = ZoneId.SHORT_IDS;

		outr("zonesMap:",zonesMap.toString().replaceAll(",",",\t"));
		//=> zonesMap: {CTT=Asia/Shanghai,	 ART=Africa/Cairo,	 CNT=America/St_Johns,
		// PRT=America/Puerto_Rico,	 PNT=America/Phoenix,	 PLT=Asia/Karachi,	 AST=America/Anchorage,	 BST=Asia/Dhaka,	 CST=America/Chicago,	 EST=-05:00,	 HST=-10:00,	 JST=Asia/Tokyo,	 IST=Asia/Kolkata,	 AGT=America/Argentina/Buenos_Aires,	 NST=Pacific/Auckland,	 MST=-07:00,	 AET=Australia/Sydney,	 BET=America/Sao_Paulo,	 PST=America/Los_Angeles,	 ACT=Australia/Darwin,	 SST=Pacific/Guadalcanal,	 VST=Asia/Ho_Chi_Minh,	 CAT=Africa/Harare,	 ECT=Europe/Paris,	 EAT=Africa/Addis_Ababa,	 IET=America/Indiana/Indianapolis,	 MIT=Pacific/Apia,	 NET=Asia/Yerevan}

	}

	/**
	 * 获取当前不同时区的时间
	 */
	@Test
	public void getNowTimeWithZoneTest(){

		//9.获取其它时区的当前时间:
		// 注意,时区的值只能取上面7.1和7.2中包含的值,使用其它值会抛出异常.

		outr("Asia/Shanghai",':',
				LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
		//=> Asia/Shanghai : 2019-09-08T20:08:33.503

		outr("Asia/Shanghai",
				LocalDate.now(ZoneId.of("Asia/Shanghai")));
		//=> Asia/Shanghai 2019-09-08

		outr("Australia/Eucla",':',
				LocalDateTime.now(ZoneId.of("Australia/Eucla")));
		//=> Australia/Eucla : 2019-09-08T21:02:39.482

		outr("Australia/Eucla",
				LocalDate.now(ZoneId.of("Australia/Eucla")));
		//=> Australia/Eucla 2019-09-08

		outr("America/Cuiaba",':',
				LocalDateTime.now(ZoneId.of("America/Cuiaba")));
		//=> America/Cuiaba : 2019-09-08T08:08:33.506

		outr("America/Cuiaba",
				LocalDate.now(ZoneId.of("America/Cuiaba")));
		//=> America/Cuiaba 2019-09-08

		outr("Africa/Nairobi",':',
				LocalDateTime.now(ZoneId.of("Africa/Nairobi")));
		//=> Africa/Nairobi : 2019-09-08T15:08:33.506

		outr("Africa/Nairobi",
				LocalDate.now(ZoneId.of("Africa/Nairobi")));
		//=> Africa/Nairobi 2019-09-08


		//10.获取带时区的当前时间
		outr(ZonedDateTime.now());
		//=> 2019-09-08T20:39:11.723+08:00[Asia/Shanghai]

		outr(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")));
		//=> 2019-09-08T20:40:44.681+08:00[Asia/Shanghai]
	}

	/**
	 * 根据给定参数初始化LocalDateTime&LocalDate等实例.
	 */
	@Test
	public void initLocalDateTimeTest(){

		//11.1根据提供日期时间字符串初始化LocalDateTime对象.默认使用格式为:yyyy-MM-ddT:hh:mm:ss.ms
		LocalDateTime localDateTime = LocalDateTime.parse("2020-02-25T15:30:45.100");

		outr(localDateTime);//=> 2020-02-25T15:30:45.100

		//11.2根据年,月,日,时,分,秒初始化
		localDateTime = LocalDateTime.of(2025,11,25,13,20,45);

		outr(localDateTime);//=> 2025-11-25T13:20:45

		//11.3根据年,月,日,时,分初始化
		localDateTime = LocalDateTime.of(2055,10,30,12,30);

		outr(localDateTime);//=> 2055-10-30T12:30

		//上面的月份都可以枚举类Month代替

		localDateTime = LocalDateTime.of(2030,Month.FEBRUARY,20,11,50);

		outr(localDateTime);//=>2030-02-20T11:50

		//11.4根据与格林尼治时间的秒差值以及时区来初始化时间.
		localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(60*60*48),ZoneId.of("Z"));

		outr(localDateTime);//=> 1970-01-03T00:00

		localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(60*60*48),ZoneId.of("Asia/Shanghai"));

		outr(localDateTime);//=> 1970-01-03T08:00

		/*11.5根据与格林尼治时间的秒差值和纳秒调整值以及时区偏移来初始化时间
		*	我们实际使用时还是用ZonId对象来得简单明了些.
		* ZoneOffset可以了解使用,其实源码层都是互通的
		*/
		localDateTime = LocalDateTime.ofEpochSecond(60*60*48,0,ZoneOffset.of("+08"));

		outr(localDateTime);//=> 1970-01-03T08:00

		//12.根据提供日期字符串初始化LocalDate对象.默认使用格式为:yyyy-MM-dd
		LocalDate localDate = LocalDate.parse("2035-01-22");

		outr(localDate);//=> 2055-10-30T12:30

		localDate = LocalDate.of(2020,Month.JANUARY,15);

		outr(localDate);//=> 2020-01-15

		//13.根据给定时,分,秒,纳秒调整值,或者当天的秒差和纳秒差值等来初始化LocalTime对象

		//>时,分
		LocalTime localTime = LocalTime.of(12,50);

		outr(localTime);//=> 12:50

		//>时,分,秒
		localTime = LocalTime.of(13,33,26);

		outr(localTime);//=> 13:33:26

		//>时,分,秒,纳秒
		localTime = LocalTime.of(15,20,35,10);

		outr(localTime);//=> 15:20:35.000000010

		//>当天的秒值
		localTime = LocalTime.ofSecondOfDay(120*60);

		outr(localTime);//=>  02:00

		//>当天的纳秒值
		localTime = LocalTime.ofNanoOfDay((long) (60*10*1e6));

		outr(localTime);//=> 00:00:00.600

	}

	/**
	 * 读取一个LocalDateTime对象
	 */
	@Test
	public void getterForLocalDateTime(){

		LocalDateTime localDateTime = LocalDateTime.now();

		/*14.获取各个字段的值,常用值的获取都有对应的get方法*/

		outfr("年:%d",localDateTime.getYear());//=> 年:2019

		outfr("月份:%s,:%d",
				localDateTime.getMonth(),localDateTime.getMonthValue());
		//=> 月:SEPTEMBER,月:9

		outfr("当月第 %d 天,本周 %s,本年第 %d 天",
				localDateTime.getDayOfMonth(),localDateTime.getDayOfWeek(),localDateTime.getDayOfYear());
		//=> 当月第 8 天,本周 SUNDAY,本年第 251 天

		outfr("时:%2d ,分:%2d,秒:%2d,纳秒:%.2e",
				localDateTime.getHour(),localDateTime.getMinute(),localDateTime.getSecond(),
				(double) localDateTime.getNano());
		//=> 时:23 ,分: 3,秒:46,纳秒:3.02e+08

		//LocalDateTime.get()方法里面的参数可以使用 ChronoField 枚举对象,类似于Calendar的静态变量.
		outfr("年:%4d,月:%2d,日:%2d,当年第%d周,当月第%d周",
				localDateTime.get(ChronoField.YEAR),
				localDateTime.get(ChronoField.MONTH_OF_YEAR),
				localDateTime.get(ChronoField.DAY_OF_MONTH),
				localDateTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR),
				localDateTime.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
		//=> 年:2019,月: 9,日: 9,当年第36周,当月第2周

		//15.获取对应LocalDate对象
		LocalDate localDate = localDateTime.toLocalDate();

		outfr("当前日期:%s",localDate);
		//=> 当前日期:2019-09-08

		//16.获取对应LocalTime对象
		LocalTime localTime = localDateTime.toLocalTime();

		outfr("当前时间:%s",localTime);
		//=> 当前时间:23:16:16.910

	}

	/**
	 * 常用日期和时间的获取
	 */
	@Test
	public void getterForFavoriteDates(){

		Supplier<LocalDateTime> getTodayStart=()->LocalDate.now().atStartOfDay();

		outfr("今日零时:%s",getTodayStart.get());
		//=> 今日零时:2019-09-09T00:00

		outfr("明日零时:%s",getTodayStart.get().plusDays(1));
		//=> 明日零时:2019-09-10T00:00

		outfr("今日最后一秒:%s",getTodayStart.get().minusSeconds(1));
		//=> 今日最后一秒:2019-09-08T23:59:59

		outfr("今日最后一毫秒:%s",getTodayStart.get().minusNanos((long)1e6));
		//=> 今日最后一毫秒:2019-09-08T23:59:59.999

		outfr("今日最后一纳秒:%s",getTodayStart.get().minusNanos(1));
		//=> 今日最后一纳秒:2019-09-08T23:59:59.999999999

		outfr("本月第一天:%s",LocalDate.now().withDayOfMonth(1));
		//=> 本月第一天:2019-09-01

		outfr("本月最后一天:%s",LocalDate.now().withDayOfMonth(1).plusMonths(1).minusDays(1));
		//=> 本月最后一天:2019-09-30

		outfr("本月共 %d 天",LocalDate.now().lengthOfMonth());
		//=> 本月共 30 天

		outfr("次月第一天:%s",LocalDate.now().plusMonths(1).withDayOfMonth(1));
		//=> 次月第一天:2019-10-01

		LocalDateTime  nowTime = LocalDateTime.now();

		outfr("当前时间:%s",nowTime);//=> 当前时间:2019-09-09T11:47:23.573

		outfr("%d个小时后的时间是:%s",6,nowTime.plusHours(6));
		//=> 6个小时后的时间是:2019-09-09T17:47:23.573

		outfr("%d分钟后的时间是:%s",90,nowTime.plusMinutes(90));
		//=> 90分钟后的时间是:2019-09-09T13:17:23.573

		outfr("%d个月之后的时间是:%s",3,nowTime.plusMonths(3));
		//=> 3个月之后的时间是:2019-12-09T11:47:23.573

		outfr("%d天后的时间是:%s",3,nowTime.plusDays(3));
		//=> 3天后的时间是:2019-09-12T11:47:23.573

	}

	/**
	 * 时间校验和比较
	 */
	@Test
	public void timeCheck(){

		LocalDateTime nowTime = LocalDateTime.now();

		outfr("今年%s是不是闺年? =>%s",nowTime.getYear(),nowTime.toLocalDate().isLeapYear()?'是':'否');
		//=> 今年2019是不是闺年? =>否

		LocalDateTime otherTime = LocalDateTime.of(2019,10,1,8,28,28,(int)888e6);

		outfr("另一个时间:%s",otherTime);
		//=> 另一个时间:2019-10-01T08:28:28.888

		outfr("%s 是否在 %s 之前? => %c",nowTime,otherTime,nowTime.isBefore(otherTime)?'是':'否');
		//=> 2019-09-09T13:02:09.040 是否在 2019-10-01T08:28:28.888 之前? => 是

		outfr("%s 是否在 %s 之后? => %c",nowTime,otherTime,nowTime.isAfter(otherTime)?'是':'否');
		//=> 2019-09-09T13:02:09.040 是否在 2019-10-01T08:28:28.888 之后? => 否


	}



}
