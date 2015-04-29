from datetime import date
from calendar import monthcalendar


def meetup_day(year, month, day, nth):
	# creates list with each entry being a week of the month
	days_and_weeks = monthcalendar(year,month)
	
	nth = nth.lower().strip()
	if nth in "1st":
		mod = 0
	elif nth in "2nd":
		mod = 1
	elif nth in "3rd":
		mod = 2
	elif nth in "4th":
	  mod = 3
	elif nth in "last":
		mod = 4
	else:
		mod = 13 #-teenth
		
	day = day.lower().strip()
	
	if day in "monday":
		day_num = 0
	elif day in "tuesday":
		day_num = 1
	elif day in "wednesday":
		day_num = 2
	elif day in "thursday":
		day_num = 3
	elif day in "friday":
		day_num = 4
	elif day in "saturday":
		day_num = 5
	else:
		day_num = 6 #sunday
		

	days = [x[day_num] for x in days_and_weeks if x[day_num] != 0] #list of dates of all days of week requested
	if mod == 13:
		for day_date in days:
			if day_date <= 19 and day_date >= 13:
				composed_day = day_date
	
	elif mod == 4:
		composed_day = days[-1]
	else:
		composed_day = days[mod]


	return date(year, month, composed_day)
