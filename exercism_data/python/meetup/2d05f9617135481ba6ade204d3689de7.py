from datetime import date
def meetup_day(year, month, day, week):
	first_day = date(year,month,1).weekday() #Brings back integer 0 is monday
	if day == 'Monday':
		intDay = 0
	elif day == 'Tuesday':
		intDay = 1
	elif day == 'Wednesday':
		intDay = 2
	elif day == 'Thursday':
		intDay = 3
	elif day == 'Friday':
		intDay = 4
	elif day == 'Saturday':
		intDay = 5
	elif day == 'Sunday':
		intDay = 6
	start_difference = first_day - intDay
	
	if start_difference < 0:
		start_count = -1 * start_difference + 1
	elif start_difference == 0:
		start_count = 1
	else:
		start_count = 7 - (start_difference - 1)

	if week == '1st':
		return date(year, month, start_count)
	elif week == '2nd':
		return date(year, month, start_count + 7)
	elif week == '3rd':
		return date(year, month, start_count + 14)
	elif week == '4th':
		return date(year, month, start_count + 21)
	elif week == 'last':
		if start_count + 21 <= 24:
			return date(year, month, start_count + 28)
		else:
			return date(year, month, start_count + 21)
	elif week == 'teenth':
		for i in range(1, 3):
			
			if (i * 7) + start_count < 20 and (i * 7) + start_count > 12:
				return date(year, month, (i * 7) + start_count)
