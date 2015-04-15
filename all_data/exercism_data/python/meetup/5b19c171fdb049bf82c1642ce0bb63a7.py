from datetime import date
import calendar

def meetup_day(year, month, weekday, choice):
	a = calendar.monthrange(year, month) 
	weekday_num = dict(zip(['Monday', 'Tuesday' , 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'], [0,1,2,3,4,5,6]))
	diff = a[0] - weekday_num[weekday]
	if diff > 0:
		diff = 7 - diff 
	elif diff < 0:
		diff = abs(diff) 

	multiples = dict(zip(['1st', '2nd', '3rd', '4th', '5th'], [0,1,2,3,4]))
	if choice in multiples:
		if diff + 7*multiples[choice] > a[1]:
			raise NameError('MeetupDayException')
		else:
			return date(year, month, 1 + diff + 7*multiples[choice])
			
	if choice == 'teenth':
		if diff >= 5:
			return date(year, month, 1 + diff + 7)
		else:
			return date(year, month, 1 + diff + 14)
	
	if choice == 'last':
		if 28 + diff <= a[1]:
			return date(year, month, 1 + diff + 28)
		else:
			return date(year, month, 1 + diff + 21)
