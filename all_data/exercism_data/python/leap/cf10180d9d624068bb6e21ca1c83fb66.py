class Year(object):
	def __init__(self, year):
		self.year = year

	def is_leap_year(self):
		return (self.year % 4 == 0 or (self.year % 100 != 0 and self.year % 400 == 0))
