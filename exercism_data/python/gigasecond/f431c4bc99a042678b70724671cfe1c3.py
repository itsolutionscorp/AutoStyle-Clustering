from datetime import timedelta  
  
#Add 1 day  
def add_gigasecond(date):
	return date + timedelta(seconds=10**9)
