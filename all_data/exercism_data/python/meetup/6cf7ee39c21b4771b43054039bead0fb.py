import datetime, time, calendar

def meetup_day(year,month,day,occurence):
	number = ['1st','2nd','3rd','4th']
	meet_date = datetime.date.today()

	monthLen = calendar.monthrange(year,month)[1]
	dayofweek = time.strptime(day,'%A')

	if occurence in number:
		for i in range(((int(occurence[0])-1)*7+1),(int(occurence[0])*7)+1):
			if datetime.date(year,month,i).weekday() == dayofweek[6]:
				meet_date = datetime.date(year,month,i)

	if occurence.lower() == 'last':
		for i in range(monthLen,monthLen - 7,-1):
			if datetime.date(year,month,i).weekday() == dayofweek[6]:
				meet_date = datetime.date(year,month,i)
	if occurence.lower() == 'teenth':
		for i in range(13,19):
			if datetime.date(year,month,i).weekday() == dayofweek[6]:
				meet_date = datetime.date(year,month,i)

	return meet_date
