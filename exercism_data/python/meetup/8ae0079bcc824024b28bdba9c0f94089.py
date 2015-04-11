import datetime
import calendar

days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
teenth = range(13, 20)


def meetup_day(year, month, day, pattern):
	# Get a list of all the weeks in the month, each existing of
	# a tuple with the date number and the weekday respectively.
	# For days that fall outside the month, the date number will be '0'
	weeksInMonth = calendar.Calendar().monthdays2calendar(year, month)
	indexOfDay = days.index(day)
	
	# First check for pattern 'teenth'
	if pattern == 'teenth':
		for i, week in enumerate(weeksInMonth):
			for day in week:
				if day[0] in teenth and day[1] == indexOfDay:
					return datetime.date(year, month, day[0])

	if pattern == 'last':
		weekIndex = -1
	else:
		# Cut off the last two chars and cast to int
		weekIndex = int(pattern[0:-2])-1

	# Calculate an offset (the first or last week in the month
	# might not contain the requested day)
	if pattern == 'last':
		offset = 0 if weeksInMonth[-1][indexOfDay][0] != 0 else -1
	else:
		offset = 0 if weeksInMonth[0][indexOfDay][0] != 0 else 1
	
	day = weeksInMonth[weekIndex+offset][indexOfDay][0]

	return datetime.date(year, month, day)
