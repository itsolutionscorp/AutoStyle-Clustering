import calendar
import datetime

def meetup_day(year,month,day,num):
	calendar.setfirstweekday(6)
	weeks = calendar.monthcalendar(year,month)
	day = day.lower()
	days = {'sunday':0,'monday':1,'tuesday':2,'wednesday':3,'thursday':4,'friday':5,'saturday':6}
	nums = {'1st':1,'2nd':2,'3rd':3,'4th':4,'teenth':99,'last':999}
	count = 0
	for week in weeks:
		temp = week[days[day]]
		if (temp != 0):
			count = count + 1
			if (temp >= 13) and (temp <= 19) and (num.lower() == 'teenth'):
				date = datetime.date(year,month,temp)
				break
			elif (count == nums[num.lower()]):
				date = datetime.date(year,month,temp)
				break
			elif (num.lower() == 'last') and (temp > calendar.monthrange(year,month)[1]-7):
				date = datetime.date(year,month,temp)
				break

	return date
