from datetime import date
import calendar

DAYS={'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}
OCCU={'1st':1, '2nd':2, '3rd':3, '4th':4, 'last':5, 'teenth':6}

def meetup_day(year, month, day, week):
	day_as_num=DAYS[day]
	Cal=calendar.Calendar()
	i=Cal.monthdatescalendar(year, month)
	week_num=OCCU[week]
	recurring_date=0
	for cal_week in i:
		for cal_day in cal_week:
			if cal_day.weekday() == day_as_num and cal_day.month == month:
				recurring_date+=1
				print 'same day number daynum=' + str(day_as_num) +'iteration =' +str(recurring_date)
				if week_num == 6 and cal_day.day >= 13 and cal_day.day <=19:
					return date(year, month, cal_day.day)
				if recurring_date == week_num:
					return date(year, month, cal_day.day)
	else:
		return False
