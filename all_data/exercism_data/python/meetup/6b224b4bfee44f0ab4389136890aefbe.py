from calendar import monthrange
from datetime import date

def meetup_day(year, month, day, identifier):
	'''
	This is a pretty rough version using a pretty ugly if statement 
	'''
	dayDict = {0:'Monday',1:'Tuesday',2:'Wednesday',3:'Thursday',4:'Friday',5:'Saturday',6:'Sunday'}

	for checkday in range(1,monthrange(year,month)[1]+1):
		checkdate = date(year,month,checkday)
			
		if ((identifier == '1st' and checkday in range(1,8)) or (identifier == '2nd' and checkday in range(8,15)) or \
		(identifier == '3rd' and checkday in range(15,22)) or (identifier == '4th' and checkday in range(22,29)) or \
		(identifier == '5th' and checkday in range(29,32)) or (identifier == 'teenth' and checkday in range(13,20)) or \
		(identifier == 'last' and checkday in range(monthrange(year,month)[1]-6,monthrange(year,month)[1]+1))) \
		and dayDict[checkdate.weekday()] == day:
			return date(year,month,checkday)
						
	raise ValueError('Unable to find date for meetup')
