from datetime import date
from calendar import monthcalendar


def meetup_day(year, month, day, nth):
	# creates list with each entry being a week of the month
	days_and_weeks = monthcalendar(year,month)
		
		
	mod = frequency_to_mod(nth)
	day_num = stringday_to_int(day)

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

def frequency_to_mod(frequency):
	
	frequency = frequency.lower().strip()
	if frequency in "1st":
		return 0
	elif frequency in "2nd":
		return 1
	elif frequency in "3rd":
		return 2
	elif frequency in "4th":
	  return 3
	elif frequency in "last":
		return 4
	else:
		return 13 #-teenth
		
def stringday_to_int(day):
	day = day.lower().strip()
	
	if day in "monday":
		return 0
	elif day in "tuesday":
		return 1
	elif day in "wednesday":
		return 2
	elif day in "thursday":
		return 3
	elif day in "friday":
		return 4
	elif day in "saturday":
		return 5
	else:
		return 6 #sunday
