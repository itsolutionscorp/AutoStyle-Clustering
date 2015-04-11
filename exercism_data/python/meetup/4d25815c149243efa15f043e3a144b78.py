import datetime

def last_day_of_month(date):
    if date.month == 12:
        return date.replace(day=31)
    return date.replace(month=date.month+1, day=1) - datetime.timedelta(days=1)

def meetup_day(year, month, day, number):
	weekdays = {"monday": 0, "tuesday": 1, "wednesday": 2, "thursday": 3, "friday": 4, "saturday": 5, "sunday": 6}
	cardinal = {"1st": 0, "2nd": 1, "3rd": 2, "4th": 3}
	try:
		mult = cardinal[number]
		timechange = weekdays[day.lower()] - datetime.date(year,month,1).weekday()
		timechange += 7 if timechange < 0 else 0
		timechange += mult*7
		return datetime.date(year,month,1) + datetime.timedelta(days=timechange)
	except KeyError:
		if number == "last":
			lastday = last_day_of_month(datetime.date(year,month,1))
			timechange = lastday.weekday() - weekdays[day.lower()]
			timechange += 7 if timechange < 0 else 0
			return lastday - datetime.timedelta(days=timechange)
		elif number == "teenth":
			firstteenth = datetime.date(year,month, 13)
			timechange = weekdays[day.lower()] - firstteenth.weekday()
			timechange += 7 if timechange < 0 else 0
			return firstteenth + datetime.timedelta(days=timechange)
