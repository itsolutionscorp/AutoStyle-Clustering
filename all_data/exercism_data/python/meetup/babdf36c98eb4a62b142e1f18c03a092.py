from datetime import date

def is_leap_year(x):
	if x % 4 == 0:
		if x % 100 == 0:
			if x % 400 == 0:
				return True
			else:
				return False
		else:
			return True
	else:
		return False

def meetup_day(jahr,monat, werktag, auswahl):
	if werktag == 'Monday':
		isoday = 0
	if werktag == 'Tuesday':
		isoday = 1
	if werktag == 'Wednesday':
		isoday = 2
	if werktag == 'Thursday':
		isoday = 3
	if werktag == 'Friday':
		isoday = 4
	if werktag == 'Saturday':
		isoday = 5
	if werktag == 'Sunday':
		isoday = 6
		
		
	if auswahl == '1st':
		i = 1
		while date(jahr,monat,i).weekday() != isoday:
			i = i + 1 	
		return date(jahr,monat,i)
		
	
	if auswahl == 'teenth':
		i = 10
		while date(jahr,monat,i).weekday() != isoday:
			i = i + 1 	
		return date(jahr,monat,i)
	
	if auswahl == '2nd':
		i = 1 
		z = 0
		while (date(jahr,monat,i).weekday() != isoday or z != 1):
			if date(jahr,monat,i).weekday() == isoday:
				z = z + 1
			i = i + 1
		return date(jahr,monat,i)
		
	if auswahl == '3rd':
		i = 1 
		z = 0
		while (date(jahr,monat,i).weekday() != isoday or z != 2):
			if date(jahr,monat,i).weekday() == isoday:
				z = z + 1
			i = i + 1
		return date(jahr,monat,i)
				
	if auswahl == '4th':
		i = 1 
		z = 0
		while (date(jahr,monat,i).weekday() != isoday or z < 3):
			if date(jahr,monat,i).weekday() == isoday:
				z = z + 1
			i = i + 1
		return date(jahr,monat,i)
		
	if auswahl == 'last':
		if monat == 2:
			if is_leap_year(jahr):
				i = 29
			else:
				i = 28
		else:
			if monat % 2 == 0:
				i = 31
			else:
				i =30
			
		while date(jahr,monat,i).weekday() != isoday:
			i -= 1
		return date(jahr,monat,i)
