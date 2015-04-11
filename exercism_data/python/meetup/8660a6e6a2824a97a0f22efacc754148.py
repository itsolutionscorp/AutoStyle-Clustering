import datetime

def meetup_day(year,month,weekday,position):
	dayEnum = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}
	
	monthStart = datetime.date(year, month, 1).weekday()	# Find the weekday of the first day of the month
	day = dayEnum[weekday] - monthStart + 1 				# Find the first instance of passed 'weekday' in the month
	if day < 1: day += 7 

	if position == 'teenth':
		while day < 13 or day > 19:							# Iterate by week to the teenth
			day += 7
		return datetime.date(year,month,day)

	elif position == 'last':
		monthEnd = datetime.date(year,month + 1,1) + datetime.timedelta(days=-1)
		if monthEnd.weekday() > dayEnum[weekday]:
			return monthEnd + datetime.timedelta(days=-(dayEnum[weekday] - monthEnd.weekday()))
		else:
			return monthEnd + datetime.timedelta(days=-(monthEnd.weekday() - dayEnum[weekday]))
	
	else:
		day += 7*(int(position[:-2])-1)						# Move forward by the number of weeks indicated

		return datetime.date(year,month,day)


# # Meetup

# Calculate the date of meetups.

# Typically meetups happen on the same day of the week.

# Examples are

# - the first Monday
# - the third Tuesday
# - the Wednesteenth
# - the last Thursday

# There are exactly 7 days that end in '-teenth'. Therefore, one is
# guaranteed that each day of the week will have a '-teenth' in every
# month.
