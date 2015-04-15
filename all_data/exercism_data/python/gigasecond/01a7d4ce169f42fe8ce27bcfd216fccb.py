import datetime

def add_gigasecond(dob):
	gigaSecInDays = 10**9/3600/24 # 1 billion seconds equals 11574 days
	return dob + datetime.timedelta(gigaSecInDays)
