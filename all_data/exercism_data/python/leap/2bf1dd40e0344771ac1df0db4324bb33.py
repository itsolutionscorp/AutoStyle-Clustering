class Year(object):
	def __init__(self, year):
		self.year = year

	def is_leap_year(self):
		"""A leap year happens on every year that is evenly divisible by 400
		   and every year that is evenly divisible by 4
		   unless that year is also evenly divisible by 100."""
		return (self.year % 400 == 0 or
				(self.year % 4 == 0 and self.year % 100 != 0))
