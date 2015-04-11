import datetime 

def add_gigasecond(birthdate):
	
	gigasecond_days = 10**9/(60*60*24)
	gs_anniversary = birthdate + datetime.timedelta(days=gigasecond_days)
	return gs_anniversary
