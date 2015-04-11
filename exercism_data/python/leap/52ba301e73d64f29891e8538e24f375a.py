class is_leap_year(object):
	'''Test if a number is leap year or not.'''
	def __init__(self, year):
		self.year = year
		if (year % 4 == 0):
			if (year % 100 == 0):
				if (year % 400 == 0):
					True
				else:
					False
			else:
				True
		else:
			False
