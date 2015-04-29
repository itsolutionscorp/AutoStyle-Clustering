class Year(object):
	def __init__(self, year):
		self.year = year

	def is_leap_year(self):
		if ((self.year % 4) == 0):
			if ((self.year % 100) == 0):
				if ((self.year % 400 == 0)):
					return True
				else:
					False
			else:
				return True		
		else:
			return False
			
			
			
