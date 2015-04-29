from datetime import date


##Time refers to the occurence of the day
time_dict = {'1st':1, '2nd':2, '3rd':3, '4th':4}
weekday_dict = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}
teenth_dict = [13,14,15,16,17,18,19]

def meetup_day(year, month, day, time):
	if time == 'last':
		last_day=0
		for a in range( 1,(date(year,month+1, 1) - date(year, month,1)).days):
			if date(year, month, a).weekday() == weekday_dict[day]:
				last_day=a
		return date(year, month, last_day+7)
	elif time == 'teenth':
		for b in range( 1,(date(year,month+1, 1) - date(year, month,1)).days):
			if date(year, month, b).weekday() == weekday_dict[day]:
				for d in teenth_dict:
					if b == d:
						return date(year, month, b)
	else:
		occurence_count = 0
		for w in range( 1,(date(year,month+1, 1) - date(year, month,1)).days):
			if date(year, month, w).weekday() == weekday_dict[day]:
				occurence_count+=1
				if occurence_count == time_dict[time]:
					return date(year,month,w)
