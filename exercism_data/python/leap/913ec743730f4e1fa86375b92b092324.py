class Year(object):

	def __init__(self, y):
		self.y = int(y)


	def is_leap_year(self):
		return self.y % 400 == 0 or (self.y % 4 == 0 and self.y % 100 != 0)
		#if (self.y % 4) == 0: 
		#	return True
		#elif (self.y % 100) != 0:
		#	return False
		#elif (self.y % 400) != 0: 
		#	return False
