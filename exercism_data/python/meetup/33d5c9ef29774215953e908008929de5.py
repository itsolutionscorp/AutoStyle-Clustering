from datetime import date
from year import is_leap_year
#finds date of meetup for given month and year
def meetup_day(year, month, day_of_week, signifier) :
	if day_of_week == 'Monday':
		day_of_week = 0
	elif day_of_week == 'Tuesday':
		day_of_week = 1
	elif day_of_week == 'Wednesday':
		day_of_week = 2
	elif day_of_week == 'Thursday':
		day_of_week = 3
	elif day_of_week == 'Friday':
		day_of_week = 4
	elif day_of_week == 'Saturday':
		day_of_week = 5
	else: #day of week == Sunday
		day_of_week = 6

	if signifier[0].isdigit():
		first_day = date(year, month, 1).weekday()

		offset = (day_of_week - first_day)+1
		if offset < 1:
			offset += 7

		if signifier == '1st':
			return date(year, month, offset)
		elif signifier == '2nd':
			return date(year, month, offset+7)
		elif signifier == '3rd':
			return date(year, month, offset+14)
		else: #signifier == '4th'
			return date(year, month, offset+21)

	elif signifier == 'last':
		if month in ['January', 'March', 'May', 'July', 'August', 'October', 'December'] :
			len_of_month = 31
		elif month in ['April', 'June', 'September', 'November']:
			len_of_month = 30
		else: #month == February
			if is_leap_year(year):
				len_of_month = 29
			else:
				len_of_month = 28

		last_day = date(year, month, len_of_month).weekday()
		offset = last_day - day_of_week

		#works with tests but could easily return faulty date - what's a better way to do this?
		return date(year, month, len_of_month - offset)

	else: #signifer == 'teenth'
		the_13th = date(year,month, 13).weekday()

		offset = (day_of_week - the_13th)+1
		if offset < 1:
			offset +=7
		return date(year, month, offset+12)
