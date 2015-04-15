from datetime import timedelta

def add_gigasecond(t1):
	t2 = t1 + timedelta(seconds = 1000000000)
	return t2
