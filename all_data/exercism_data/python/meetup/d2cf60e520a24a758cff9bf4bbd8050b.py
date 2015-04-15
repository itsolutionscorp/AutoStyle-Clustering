#/usr/bin/env python
from datetime import date
from calendar import monthrange

def meetup_day(year, month, day, pos):	
	days = ("Monday","Tuesday", "Wednesday", 
			"Thursday", "Friday", "Saturday", 
			"Sunday")

	first_id, len_month = monthrange(year, month) # int 0-6, int 28-31	
	# map all week day ids to month days, start from first day id
	all_ids = [i % 7 for i in xrange(first_id, len_month + first_id)]	
	# list all dates that given day falls on in given month
	day_id = days.index(day)
	count = [i + 1 for i in xrange(len_month) if all_ids[i] == day_id]
	
	pos_dict = {"1st": count[0],
				"2nd": count[1], 
				"3rd": count[2],
				"4th": count[3], 
				"last": count[-1],
				"teenth": (set(range(13, 20)) & set(count)).pop()}
	
	return date(year, month, pos_dict[pos])
