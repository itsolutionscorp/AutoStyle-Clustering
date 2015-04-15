from datetime import timedelta, date
def add_gigasecond(dateBorn):
	return dateBorn + timedelta(seconds = 10**9)

# print(add_gigasecond(date(2009, 2, 19)))

# print(date(2009,2,19) + timedelta(seconds = 10**9))
