package com.fhtiger.jdkread.java.time;

import com.fhtiger.jdkread.TestStarter;
import org.junit.Test;

import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
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

		outfr("%s 是日期时间",LocalDateTime.class);
		//=> class java.time.LocalDateTime 是日期时间对象

		outfr("%s 是日期",LocalDate.class);
		//=> class java.time.LocalDate 是日期对象

		outfr("%s 是时间",LocalTime.class);
		//=> class java.time.LocalTime 是时间对象

		outfr("%s 是时间戳",Instant.class);
		//=> class java.time.Instant 是时间戳对象

		outfr("%s 是年",Year.class);
		//=> class java.time.Year 是年

		outfr("%s 是年-月",YearMonth.class);
		//=> class java.time.YearMonth 是年-月

		outfr("%s 是月-日",MonthDay.class);
		//=> class java.time.MonthDay 是月-日

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
		//=> zones: [Asia/Aden,	 America/Cuiaba,	 Etc/GMT+9,	 Etc/GMT+8,	 Africa/Nairobi ...
		outr("---------");

		//8.2有可用简称的时区对照.
		Map<String, String> zonesMap = ZoneId.SHORT_IDS;

		outr("zonesMap:",zonesMap.toString().replaceAll(",",",\t"));
		//=> zonesMap: {CTT=Asia/Shanghai,	 ART=Africa/Cairo,	 CNT=America/St_Johns ...
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
	 * 获取当前时间的其它值
	 */
	@Test
	public void getNowOther(){

		YearMonth nowYearMonth = YearMonth.now();

		outfr("当前是: %s",nowYearMonth);
		//=> 当前是 2019-09

		outfr("今年是: %s",Year.now());
		//=> 今年是: 2019

		outfr("今天是: %s",MonthDay.now());
		//=> 今天是: --09-09

		//系统时钟:Clock.systemDefaultZone()

		 Clock systemClock= Clock.systemDefaultZone();

		outfr("当前时间戳:> %s |毫秒",systemClock.millis());
		//=> 当前时间戳:> 1568035734234 |毫秒

		outfr("当前时间戳:> %d 秒 %d 纳秒 |",systemClock.instant().getEpochSecond(),systemClock.instant().getNano());
		//=> 当前时间戳:> 1568035734 秒 235000000 纳秒 |

		outfr("当前时间戳:> %d 秒 %d 纳秒",Instant.now().getEpochSecond(),Instant.now().getNano());
		//=> 当前时间戳:> 1568035734 秒 237000000 纳秒

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
		localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(60*60*48),
				ZoneId.of("Z"));

		outr(localDateTime);//=> 1970-01-03T00:00

		localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(60*60*48),
				ZoneId.of("Asia/Shanghai"));

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
				localDateTime.getDayOfMonth(),localDateTime.getDayOfWeek(),
				localDateTime.getDayOfYear());
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
	 * 对时间对象的增减字段等操作
	 */
	@Test
	public void plusMinusLocalDateTime(){

		LocalDateTime localDateTime = LocalDateTime.of(2020,11,20,12,34,40,888);

		outfr("初始时间:%s",localDateTime);
		//=> 时间:2020-11-20T12:34:40.000000888

		outfr("增加 %d 天后的时间: %s",2,localDateTime.plusDays(2));
		//=> 增加 2 天后的时间: 2020-11-22T12:34:40.000000888

		outfr("增加 %d 秒后的时间: %s",34,localDateTime.plusSeconds(34));
		//=> 增加 34 秒后的时间: 2020-11-20T12:35:14.000000888

		outfr("增加 %d %s 后的时间: %s",25,ChronoUnit.MINUTES,
				localDateTime.plus(25,ChronoUnit.MINUTES));
		//=> 增加 25 Minutes 后的时间: 2020-11-20T12:59:40.000000888

		outfr("增加 %.0e 毫秒后的时间: %s",1e3,
				localDateTime.plus(Duration.ofMillis((long)1e3)));
		//=> 增加 1e+03 毫秒后的时间: 2020-11-20T12:34:41.000000888

		outfr("减去 %d 小时后的时间: %s",4,localDateTime.minusHours(4));
		//=> 减去 4 小时后的时间: 2020-11-20T08:34:40.000000888

		outfr("减去 %d 周后的时间: %s",1,localDateTime.minusWeeks(1));
		//=> 减去 1 周后的时间: 2020-11-13T12:34:40.000000888

		outfr("减去 %d 月后的时间: %s",2,localDateTime.minus(Period.ofMonths(2)));
		//=> 减去 2 月后的时间: 2020-09-20T12:34:40.000000888

		outfr("设置日期为 %d 后的时间为: %s",15,localDateTime.withDayOfMonth(15));
		//=> 设置日期为 15 后的时间为: 2020-11-15T12:34:40.000000888

		outfr("设置纳秒为 %d 后的时间为: %s",0,localDateTime.withNano(0));
		//=> 设置纳秒为 0 后的时间为: 2020-11-20T12:34:40

		outfr("设置 %s 为 %d 后,再增加 %d %s,然后减去 %d %s 后\n时间为: %s",
				ChronoField.YEAR,2030,2,ChronoUnit.MONTHS,3,ChronoUnit.HOURS,
				localDateTime.with(temporal -> temporal
				.with(ChronoField.YEAR,2030)
				.plus(2,ChronoUnit.MONTHS)
				.minus(3,ChronoUnit.HOURS)));
		//=> 设置 Year 为 2030 后,再增加 2 Months,然后减去 3 Hours 后
		//时间为: 2031-01-20T09:34:40.000000888

		/*truncateTo 日期截断的用法,
		截断的意思就是从指定字段值截断数据,
		即:从此字段往后的所有字段值都设置为0*/

		outfr("将 %s 从 %s 截断 => %s",localDateTime,ChronoUnit.HOURS,
				localDateTime.truncatedTo(ChronoUnit.HOURS));
		//=> 将 2020-11-20T12:34:40.000000888 从 Hours 截断 => 2020-11-20T12:00

		outfr("将 %s 从 %s 截断 => %s",localDateTime,ChronoUnit.DAYS,
				localDateTime.truncatedTo(ChronoUnit.DAYS));
		//=> 将 2020-11-20T12:34:40.000000888 从 Days 截断 => 2020-11-20T00:00

	}

	/**
	 * 常用日期和时间等值的获取方式
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

		outfr("本月第一天:%s",YearMonth.now().atDay(1));

		outfr("本月最后一天:%s",LocalDate.now().withDayOfMonth(1).plusMonths(1).minusDays(1));
		//=> 本月最后一天:2019-09-30

		outfr("本月最后一天:%s",YearMonth.now().atEndOfMonth());

		outfr("次月第一天:%s",LocalDate.now().plusMonths(1).withDayOfMonth(1));
		//=> 次月第一天:2019-10-01

		outfr("次月第一天:%s",YearMonth.now().plusMonths(1).atDay(1));

		outfr("本月共 %d 天",LocalDate.now().lengthOfMonth());
		//=> 本月共 30 天

		outfr("本月共 %d 天",YearMonth.now().lengthOfMonth());

		outfr("今年共 %d 天",YearMonth.now().lengthOfYear());
		//=> 今年共 365 天

		outfr("今年共 %d 天",Year.now().length());

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
		LocalDateTime oneTime = LocalDateTime.of(2019,2,20,5,20,40);

		outfr("今年%s是不是闺年? =>%s", oneTime.getYear(),oneTime.toLocalDate().isLeapYear()?'是':'否');
		//=> 今年2019是不是闺年? =>否

		LocalDateTime otherTime = LocalDateTime.of(2019,10,1,8,28,28,(int)888e6);

		outfr("另一个时间:%s",otherTime);
		//=> 另一个时间:2019-10-01T08:28:28.888

		outfr("%s 是否在 %s 之前? => %c",oneTime,otherTime, oneTime.isBefore(otherTime)?'是':'否');
		//=> 2019-09-09T13:02:09.040 是否在 2019-10-01T08:28:28.888 之前? => 是

		outfr("%s 是否在 %s 之后? => %c",oneTime,otherTime,oneTime.isAfter(otherTime)?'是':'否');
		//=> 2019-09-09T13:02:09.040 是否在 2019-10-01T08:28:28.888 之后? => 否

		/*比较两个时间,比较顺序为:year->month->day->hour->minute->second->nano,
		* 某一项比较结果不为0,则直接返回比较结果,为0则往后比较.
		* 如果比较参数非LocalDateTime实例,在上面的比较顺序后还会有一项日历系统的比对.*/

		outfr("%s compareTo %s => %s",oneTime,otherTime,oneTime.compareTo(otherTime));
		//=> 2019-02-20T05:20:40 compareTo 2019-10-01T08:28:28.888 => -8

		outfr("%s compareTo %s => %s",oneTime,otherTime.withMonth(oneTime.getMonthValue()),
				oneTime.compareTo(otherTime.withMonth(oneTime.getMonthValue())));
		//=> 2019-02-20T05:20:40 compareTo 2019-02-01T08:28:28.888 => 19

		/*!比较两个LocalDateTime对象,参数非LocalDateTime对象直接返回false*/

		outfr("%s equals %s = %s",
				oneTime.truncatedTo(ChronoUnit.DAYS),
				otherTime.withMonth(2).withDayOfMonth(20).truncatedTo(ChronoUnit.DAYS),
				oneTime.truncatedTo(ChronoUnit.DAYS)
						.equals(otherTime.withMonth(2).withDayOfMonth(20).truncatedTo(ChronoUnit.DAYS)));
		//=> 2019-02-20T00:00 equals 2019-02-20T00:00 = true

		outfr("%s equals %s = %s",
				oneTime.truncatedTo(ChronoUnit.DAYS),oneTime.toLocalDate(),
				oneTime.truncatedTo(ChronoUnit.DAYS).equals(oneTime.toLocalDate()));
		//=> 2019-02-20T00:00 equals 2019-02-20 = false

		/*!比较时间点,参数不限于LocalDateTime对象.*/

		otherTime = LocalDateTime.from(oneTime);
		outfr("%s isEqual %s = %s",oneTime,otherTime,oneTime.isEqual(otherTime));
		//=> 2019-02-20T05:20:40 isEqual 2019-02-20T05:20:40 = true

		oneTime = oneTime.truncatedTo(ChronoUnit.DAYS);
		ChronoLocalDateTime otherChrono = ChronoLocalDateTime.from(oneTime);
		outfr("%s isEqual %s = %s",oneTime,otherChrono,oneTime.isEqual(otherChrono));
		//=> 2019-02-20T00:00 isEqual 2019-02-20T00:00 = true

	}

	@Test
	public void diffTime(){

		LocalDateTime oneTime = LocalDateTime.of(2019,2,20,5,20,40);
		LocalDateTime twoTime = LocalDateTime.of(2020,3,15,4,15,50);

		outfr("%s 与 %s 差 %d 天",oneTime,twoTime,ChronoUnit.DAYS.between(oneTime,twoTime));
		//=> 2019-02-20T05:20:40 与 2020-03-15T04:15:50 差 388 天

		outfr("%s 与 %s 差 %d 月",oneTime,twoTime,ChronoUnit.MONTHS.between(oneTime,twoTime));
		//=> 2019-02-20T05:20:40 与 2020-03-15T04:15:50 差 12 月

		outfr("%s 到 %s 差 %d 小时",oneTime,twoTime,oneTime.until(twoTime,ChronoUnit.HOURS));
		//=> 2019-02-20T05:20:40 到 2020-03-15T04:15:50 差 9334 小时

		outfr("%s 到 %s 差 %d 分钟",twoTime,oneTime,twoTime.until(oneTime,ChronoUnit.MINUTES));
		//=> 2020-03-15T04:15:50 到 2019-02-20T05:20:40 差 -560095 分钟

	}

	/**
	 * 字符串转日期与日期转字符串
	 * 格式化的使用.
	 */
	@Test
	public void str2DateAndDate2Str(){

	/*
	 *格式器中的字符对应的含义.
	 *<pre>
	 *  Symbol  Meaning                     Presentation      Examples
	 *  ------  -------                     ------------      -------
	 *   G       era                         text              AD; Anno Domini; A
	 *   u       year                        year              2004; 04
	 *   y       year-of-era                 year              2004; 04
	 *   D       day-of-year                 number            189
	 *   M/L     month-of-year               number/text       7; 07; Jul; July; J
	 *   d       day-of-month                number            10
	 *
	 *   Q/q     quarter-of-year             number/text       3; 03; Q3; 3rd quarter
	 *   Y       week-based-year             year              1996; 96
	 *   w       week-of-week-based-year     number            27
	 *   W       week-of-month               number            4
	 *   E       day-of-week                 text              Tue; Tuesday; T
	 *   e/c     localized day-of-week       number/text       2; 02; Tue; Tuesday; T
	 *   F       week-of-month               number            3
	 *
	 *   a       am-pm-of-day                text              PM
	 *   h       clock-hour-of-am-pm (1-12)  number            12
	 *   K       hour-of-am-pm (0-11)        number            0
	 *   k       clock-hour-of-am-pm (1-24)  number            0
	 *
	 *   H       hour-of-day (0-23)          number            0
	 *   m       minute-of-hour              number            30
	 *   s       second-of-minute            number            55
	 *   S       fraction-of-second          fraction          978
	 *   A       milli-of-day                number            1234
	 *   n       nano-of-second              number            987654321
	 *   N       nano-of-day                 number            1234000000
	 *
	 *   V       time-zone ID                zone-id           America/Los_Angeles; Z; -08:30
	 *   z       time-zone name              zone-name         Pacific Standard Time; PST
	 *   O       localized zone-offset       offset-O          GMT+8; GMT+08:00; UTC-08:00;
	 *   X       zone-offset 'Z' for zero    offset-X          Z; -08; -0830; -08:30; -083015; -08:30:15;
	 *   x       zone-offset                 offset-x          +0000; -08; -0830; -08:30; -083015; -08:30:15;
	 *   Z       zone-offset                 offset-Z          +0000; -0800; -08:00;
	 *
	 *   p       pad next                    pad modifier      1
	 *
	 *   '       escape for text             delimiter
	 *   ''      single quote                literal           '
	 *   [       optional section start
	 *   ]       optional section end
	 *   #       reserved for future use
	 *   {       reserved for future use
	 *   }       reserved for future use
	 * </pre>
	 * */

		/*使用预定义格式器解析字符串成日期
		* java.time.format.DateTimeFormatter 提供了很多内置的格式器,可以拿来使用.*/

		String strFormatter = "预置> %s ,转换%s =>[%s] %s ";

		StringBuilder strDate = new StringBuilder("20190225");

		DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;

		LocalDate date = LocalDate.parse(strDate, formatter);

		outfr(strFormatter,"BASIC_ISO_DATE",
				strDate,date.getClass(),date.format(formatter));
		//=> 预置> BASIC_ISO_DATE ,转换20190225 =>[class java.time.LocalDate] 20190225

		formatter = DateTimeFormatter.ISO_DATE;

		strDate.replace(0,strDate.length(),"2019-03-22");

		date = LocalDate.parse(strDate,formatter);

		outfr(strFormatter,"ISO_DATE",
				strDate,date.getClass(),date.format(formatter));
		//=> 预置> ISO_DATE ,转换2019-03-22 =>[class java.time.LocalDate] 2019-03-22

		/*注意:格式器如果包含时间值,使用LocalDateTime的相关转换方法*/

		formatter = DateTimeFormatter.ISO_DATE_TIME;

		strDate.replace(0,strDate.length(),"2019-03-22T12:25:30");

		 LocalDateTime dateTime = LocalDateTime.parse(strDate,formatter);

		outfr(strFormatter,"ISO_DATE_TIME",
				strDate,dateTime.getClass(),dateTime.format(formatter));
		//=> 预置> ISO_DATE_TIME ,转换2019-03-22T12:25:30
		// =>[class java.time.LocalDateTime] 2019-03-22T12:25:30

		/*-----------------------------------------------------*/

		/*自定义格式器来解析日期字符串*/

		strFormatter=strFormatter.replace("预置","自定义");

		formatter = DateTimeFormatter.ofPattern("yyyy=>MMdd");

		strDate.replace(0,strDate.length(),"2020=>1020");

		date = LocalDate.parse(strDate,formatter);

		outfr(strFormatter,"yyyy=>MMdd",strDate,date.getClass(),date.format(formatter));
		//=> 自定义> yyyy=>MMdd ,转换2020=>1020 =>[class java.time.LocalDate] 2020=>1020

		/*获取格式器的第2个参数可以选择国际化语言,
		像如果日期字符串中有英文的月份,就需要使用Locale.ENGLISH参数*/

		formatter = DateTimeFormatter.ofPattern("MMM dd yy", Locale.ENGLISH);

		strDate.replace(0,strDate.length(),"Jan 20 30");

		date = LocalDate.parse(strDate,formatter);

		outfr(strFormatter,"MMM dd yy",strDate,date.getClass(),date.format(formatter));
		//=> 自定义> MMM dd yy ,转换Jan 20 30 =>[class java.time.LocalDate] Jan 20 30

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		strDate.replace(0,strDate.length(),"2035-12-20 12:23:45");

		dateTime = LocalDateTime.parse(strDate,formatter);

		outfr(strFormatter,"yyyy-MM-dd HH:mm:ss",strDate,dateTime.getClass(),dateTime.format(formatter));
		//=> 自定义> yyyy-MM-dd HH:mm:ss ,
		// 转换2035-12-20 12:23:45 =>[class java.time.LocalDateTime] 2035-12-20 12:23:45

		/*-----------------------------------------------------*/

		/*使用格式器将日期格式化为字符串*/

		strFormatter = "格式:%s,结果:%s";

		formatter = DateTimeFormatter.ofPattern("yy-MM-dd|HH:mm:ss");

		outfr(strFormatter,"yy-MM-dd|HH:mm:ss",dateTime.format(formatter));
		//=> 格式:yy-MM-dd|HH:mm:ss,结果:35-12-20|12:23:45

		formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

		outfr(strFormatter,"dd MMM yyyy HH:mm",dateTime.format(formatter));
		//=> 格式:dd MMM yyyy HH:mm,结果:20 十二月 2035 12:23

		formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm",Locale.ENGLISH);

		outfr(strFormatter,"dd MMM yyyy HH:mm (Locale.ENGLISH)",dateTime.format(formatter));
		//=> 格式:dd MMM yyyy HH:mm (Locale.ENGLISH),结果:20 Dec 2035 12:23


	}

	/**
	 * Date与LocalDateTime转换.
	 */
	@Test
	public void forDate2LocalDateAndReverse(){

		/*将 java.util.Date 转换成 java.time.LocalDate(Time)*/

		Date date  =new Date();

		Instant instant = date.toInstant();

		ZoneId zoneId  = ZoneId.systemDefault();

		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

		LocalDate outLocalDate = localDateTime.toLocalDate();

		/*将 java.time.LocalDate(Time) 转换成 java.util.Date*/

		LocalDateTime inLocalDateTime = LocalDateTime.now();

		ChronoZonedDateTime<LocalDate> zonedDateTime = inLocalDateTime.atZone(zoneId);

		Date outDate = Date.from(zonedDateTime.toInstant());

	}

}
