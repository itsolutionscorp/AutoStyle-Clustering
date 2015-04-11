import datetime

def meetup_day(year,month,str1,str2):
	dates = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5,'Sunday':6}
	interval = {'1st':0, '2nd':1, '3rd':2, '4th':3, '5th':4, 'teenth':5,'first':0,'last':6}
	dayOfWeek = dates[str1]
	monthFirstDay = datetime.datetime(year,month,1).weekday()
	# monthLastDay = (datetime.datetime(year,month+1,1).weekday() - 1) % 7
	occur = interval[str2]
	# day_ls_month_first = (dayOfWeek - monthFirstDay)%7

	if occur in [0,1,2,3,4]:
		monthFirstDay = datetime.datetime(year,month,1).weekday()
		day_ls_month_first = (dayOfWeek - monthFirstDay)%7		
		return datetime.date(year,month,7*occur+day_ls_month_first+1)
	if occur == 5:
		firstDayofInterest = datetime.datetime(year,month,13).weekday()
		return datetime.date(year,month, (dayOfWeek - firstDayofInterest)%7 + 13 )
	if occur == 6:
		lastDayofInterest = datetime.datetime(year,month+1, 1 ) - datetime.timedelta(days=1)
		diff = (lastDayofInterest.weekday() - dayOfWeek )%7  
		return datetime.date(year,month, lastDayofInterest.day - diff)
