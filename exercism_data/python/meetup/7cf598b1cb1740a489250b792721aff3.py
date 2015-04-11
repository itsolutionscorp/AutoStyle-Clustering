from datetime import date
from datetime import timedelta

def meetup_day(startyear, startmonth, startstring1, startstring2):
	startdate = date(startyear, startmonth, 1)
	interval = timedelta(days=1)
	counter = startdate
	list_of_days = []

	#This section is assigning the variable 'weekday' to a value from 0-6 representing the days of the week, as this is what the weekday() function returns
	weekdays = {
				'Monday': 0, 
				'Tuesday': 1, 
				'Wednesday': 2, 
				'Thursday': 3, 
				'Friday': 4, 
				'Saturday': 5, 
				'Sunday': 6
				}

	for w in weekdays:

		if startstring1 == w:

			weekday = weekdays.get(w)

	#This section is creating a list of dates which match the weekday 'list_of_dates'
	while counter.month == startdate.month:

		if counter.month == startdate.month and counter.weekday() == weekday:

			list_of_days += [counter]

		counter += interval

	#And now we are going to start checking which day of the month it will be.
	if startstring2 == '1st':
		return list_of_days[0]

	if startstring2 == '2nd':
		return list_of_days[1]

	if startstring2 == '3rd':
		return list_of_days[2]

	if startstring2 == '4th':
		return list_of_days[3]

	if startstring2 == 'last':
		return list_of_days[-1]

	if startstring2 == 'teenth':

		x = 0

		for w in list_of_days:

			if w.day in range(13, 20):

				return list_of_days[x]

			x += 1
