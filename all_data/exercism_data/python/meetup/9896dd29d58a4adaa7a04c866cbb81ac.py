from datetime import date
from calendar import monthrange

def meetup_day(year, month, day, week):
#Given a year, month, day of the week and 
#week, return a specific date
	days= {
		"Monday" : 0,
		"Tuesday" : 1, 
		"Wednesday" : 2,
		"Thursday" : 3,
		"Friday" : 4, 
		"Saturday" : 5, 
		"Sunday" : 6
	}
	
	
	day_of_week_number = days[day]
	
	if week == "1st":
		day_number = 1
	elif week == "2nd":
		day_number = 8
	elif week == "3rd":
		day_number = 15
	elif week == "4th":
		day_number = 22
	elif week == "teenth":
		day_number = 13
	else:
		#finds the number of days in that particular month,
		#then gives a number 6 less than that
		day_number = monthrange(year, month)[1] - 6
		
	for i in range(1,32):
		if date(year, month, i).weekday() == day_of_week_number and i >= day_number:
			return date(year, month, i)
