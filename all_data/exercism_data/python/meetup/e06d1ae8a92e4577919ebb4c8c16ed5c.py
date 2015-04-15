import calendar
from datetime import date

def meetup_day(year, month, weekday, term):

	
	dayIndex = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
	
	# Find the starting day of the week and number of days in month
	monthRange = calendar.monthrange(year, month)
	# Set the day equal to the 1st weekday specified.
	day = 1 + ((dayIndex.index(weekday) - monthRange[0]) % 7)	
	
	# Increment the day to the correct date indicated by term
	if term == "teenth":
		while day < 12:
			day += 7
	elif term == "last":
		while day <= (monthRange[1]-7):
			day += 7		
	else:
		term_conversion = {"1st":0, "2nd":1, "3rd":2, "4th":3 }
		day += 7*term_conversion[term]
	
	return date(year, month, day)
