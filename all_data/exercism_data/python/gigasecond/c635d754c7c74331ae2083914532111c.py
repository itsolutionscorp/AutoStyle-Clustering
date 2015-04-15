import datetime

#calculate the Gs anniversary for a given date of birth (dob)
def add_gigasecond(dob):
	change = datetime.timedelta(seconds = 10**9)
	anniversary = dob+change
	return anniversary
