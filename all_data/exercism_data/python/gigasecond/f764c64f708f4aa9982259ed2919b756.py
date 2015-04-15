from datetime import date, timedelta

# adds secs seconds to the first second of day date, returns the resulting date.
# assumes the same time zone
def add_seconds(day, secs):
	return day + timedelta(days=secs/(60*60*24))

# calculates the gigasecond day relative to the given birthday. assumes the birth occurred on the first second of the given day.
def add_gigasecond(bday):
	return add_seconds(bday, 10**9)
