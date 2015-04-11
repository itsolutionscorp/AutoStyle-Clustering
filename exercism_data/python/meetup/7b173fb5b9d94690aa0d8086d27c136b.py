from datetime import date,datetime
import calendar

def meetup_day(year,month,weekday,x):
	weekdays = {'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3,'Friday':4,
				'Saturday' : 5, 'Sunday' : 6}
	if x is 'teenth':
		mteenths = {date.weekday(datetime(year,month,x)) : x for x in range(13,20)}
		return date(year,month,mteenths[weekdays[weekday]])

	elif x in ("1st","2nd","3rd","4th"):
		firstday = date.weekday(datetime(year,month,1))
		a = (weekdays[weekday] - firstday) % 7
		nrs = {"1st":0, "2nd":1,"3rd":2,"4th":3}
		daynr = nrs[x] * 7 + a + 1
		return date(year,month,daynr)

	elif x is 'last':
		mor = calendar.monthrange(year,month)
		lastday = date.weekday(datetime(year,month,mor[1]))
		a = (lastday - weekdays[weekday])  % 7
		daynr = mor[1] - a
		return date(year,month,daynr)
