import datetime

def first_day_of_month(year, month):
	# We need to know the day name of the first of the month.
	d = datetime.date(year, month, 1)
	return d.strftime("%A")

def last_of_month(year, month):
	# Return a date object representing the last day of the month.
	# Include a year so we don't have to do the leap year work.
	if month == 12:
		d = datetime.date(year + 1, 1, 1)
	else:
		d = datetime.date(year, month + 1, 1)
	
	return d + datetime.timedelta(days=-1)

def days_in_month(year, month, dayname):
	# Return the number of days in a month with a given day name.
	counter = 0
	for days in range(1, last_of_month(year, month).day + 1):
		d = datetime.date(year, month, days)
		if d.strftime("%A") == dayname:
			counter += 1
	return counter
		
def meetup_day(year, month, day, desc):
	# First, figure out this whole "1st, 2nd, 3rd, teenth, last" descriptor.
	# Counted weeks are easy. Strip the integer.
	day_id = desc[:1]

	# If it's a teenth, that limits us to just 7 possible days: 13-19.
	if day_id == 't':
		for days in range(13,20):
			d = datetime.date(year, month, days)
			if d.strftime("%A") == day:
				return datetime.date(year, month, days)
	
	# Some months have 4, some have 5 instances of a day. 
	# 	Find out how many with some meta functions.
	if desc == 'last':
		day_id = days_in_month(year, month, day) 
		
	if desc == 'first':
		day_id = 1
		
	# A couple initial values, starting with the first of the month.
	current_day_of_month = datetime.date(year, month, 1)
	week_counter = 1
	
	# Loop over incremented days till we find a match on name and the "th" 
	# 	week, while counting the weeks.
	while True:
		if current_day_of_month.strftime("%A") == day and week_counter == int(day_id):
			return current_day_of_month
		
		current_day_of_month = current_day_of_month + datetime.timedelta(days=1)
		
		# If we've reached the first of the month's name, increment the week.
		if current_day_of_month.strftime("%A") == first_day_of_month(year, month):
			week_counter += 1
			
	return current_day_of_month
	
