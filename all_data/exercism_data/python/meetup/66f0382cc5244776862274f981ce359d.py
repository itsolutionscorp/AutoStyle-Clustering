from datetime import date, timedelta
from dateutil.relativedelta import relativedelta

# map human readable weekdays to python weekday numbers
weekdays = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}

# define a list of teen days
teens = [13, 14, 15, 16, 17, 18, 19]

# for being able to iterate over a date range
def daterange(start_date, end_date):
    for n in range(int ((end_date - start_date).days)):
        yield start_date + timedelta(n)

def meetup_day(year, month, day, ordinal):
	# start a one month range with the first day of the month
	begin = date(year, month, 1)
	end = begin + relativedelta(months=+1)
	
	# this will store all requested weekdays in the month (ex. all Mondays)
	dates = []
	
	for i in daterange(begin, end):
		if i.weekday() == weekdays[day]:
			dates.append(i)
			
	if ordinal == "1st":
		return dates[0]
	elif ordinal == "2nd":
		return dates[1]
	elif ordinal == "3rd":
		return dates[2]
	elif ordinal == "4th":
		return dates[3]
	elif ordinal == "last":
		return dates[-1]
	elif ordinal == "teenth":
		for i in dates:
			if i.day in teens:
				return i
	
print meetup_day(2014, 12, 'Monday', 'last')
