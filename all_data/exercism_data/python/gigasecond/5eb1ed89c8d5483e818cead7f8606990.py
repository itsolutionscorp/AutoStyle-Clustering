from datetime import timedelta

def add_gigasecond(self):
	return self + timedelta(seconds = 1000000000) # add 10^9 seconds to date entered
