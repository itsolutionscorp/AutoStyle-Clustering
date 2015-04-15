import datetime


def add_gigasecond(birthday):
	"""Calculate the date that someone celebrates their gigasecond anniversary.
	Args:
		birthday (datetime.date): The person's birthday.
	
	Returns:
		datetime.date: The person's gigasecond anniversary.

	Raises:
		TypeError: If 'birthday' is not a datetime.date.
	"""
	if type(birthday) is datetime.date:
		return birthday + datetime.timedelta(seconds=(10 ** 9))
	else:
		raise TypeError('add_gigasecond(birthday) expects birthday to be a datetime.date')
	

# Author's Notes on Coding Conventions:

	# I've coded to pep8 conventions with a few exceptions that
	# work nicely with my setup (I'm only coding for myself):
		# ignore = W191,W293		- indentation contains tabs, blank line contains whitespace
		# max-line-length = 120


# Gigasecond

# Write a program that will calculate the date that someone turned or will celebrate their 1 Gs anniversary.

# A gigasecond is one billion (10**9) seconds.
