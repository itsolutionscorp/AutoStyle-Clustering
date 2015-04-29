from datetime import date

days = {'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}

def meetup_day(year, month, day, meet):
	day = days[day]
	if meet == 'teenth':
		for i in range(11,21):
			if date(year,month,i).weekday() == day:
				return date(year,month,i)
	elif meet == '1st':
		for i in range(1,31):
			try:
				if date(year,month,i).weekday() == day:
					return date(year,month,i)
			except ValueError:
				pass
	elif meet == '2nd':
		for i in range(8,31):
			try:
				if date(year,month,i).weekday() == day:
					return date(year,month,i)
			except ValueError:
				pass
	elif meet == '3rd':
		for i in range(15,31):
			try:
				if date(year,month,i).weekday() == day:
					return date(year,month,i)
			except ValueError:
				pass
	elif meet == '4th':
		for i in range(22,31):
			try:
				if date(year,month,i).weekday() == day:
					return date(year,month,i)
			except ValueError:
				pass
	elif meet == 'last':
		for i in range(31,1,-1):
			try:
				if date(year,month,i).weekday() == day:
					return date(year,month,i)
			except ValueError:
				pass
