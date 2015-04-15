from datetime import timedelta

def add_gigasecond(dob):
	GIGASECOND = 10**9 
	return dob + timedelta(seconds=GIGASECOND)
