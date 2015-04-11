import datetime
gigasecond = pow(10,9);
minute = 60;
hour = 60;
day = 24;

def add_gigasecond(bday):
	days = gigasecond/minute/hour/day;
	endDate = bday + datetime.timedelta(days=days);
	return endDate;
