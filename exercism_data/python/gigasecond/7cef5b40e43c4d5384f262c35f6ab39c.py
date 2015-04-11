import time
import datetime

def add_gigasecond(bday):
	delta = datetime.timedelta(seconds=1000000000)
	gday = bday+delta
	return gday
