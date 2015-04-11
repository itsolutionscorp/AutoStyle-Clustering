import datetime

convert = { '2nd' : 2, '3rd' : 3, '4th' : 4 } 
def meetup_day(year, month, day_name, pos):
	dt = datetime.date(year, month, 1)

	while(True):
		week_month = ((dt.day -1) // 7 + 1)

		if (pos == 'first' or pos == '1st'):
			if(dt.strftime("%A") == day_name):
				break
		if (pos == "2nd" or pos == "3rd" or pos == "4th"):
			if( week_month == convert[pos] and dt.strftime("%A") == day_name): 
				break
		if (pos == 'teenth'):
			if (dt.day >= 13 and dt.day <= 19 and dt.strftime("%A") == day_name):
				break
		if (pos == 'last'):
			if( dt.day >= 25 and dt.day <= 31 and dt.strftime("%A") == day_name):
				break

		dt += datetime.timedelta(days=1)
		if dt.day >= 31: 
			break
	
	return dt
