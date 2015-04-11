def add_gigasecond(birth_datetime):
	import datetime
	
	giga_age = birth_datetime + datetime.timedelta(0,10**9)
	return giga_age
