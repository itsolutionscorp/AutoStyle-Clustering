from datetime import timedelta

def add_gigasecond(dat):
	delta = timedelta(seconds=10**9)
	return dat + delta
