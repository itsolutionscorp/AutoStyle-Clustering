def add_gigasecond(date):
	import datetime
	date += datetime.timedelta(seconds = 1000000000)
	return date

# finds out when you have/will have lived for 1 gigasecond (1 billion seconds)	

def gigaversary():
	import datetime
	from datetime import datetime
	print "What year were you born?"
	year = int(raw_input())
	print "What month were you born?"
	month = int(raw_input())
	print "What day were you born?"
	day = int(raw_input())
	birthday = datetime(year, month, day)
	giga = add_gigasecond(birthday)
	today = datetime.today()
	
	# consider making the date formatting easier to read
	if (today > giga): 
		print "Your gigaversary was on " + str(giga)
	else: 
		print "Your gigaversary will be on " + str(giga)
		
gigaversary()
