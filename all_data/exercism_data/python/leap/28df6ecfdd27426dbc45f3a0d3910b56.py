class is_leap_year:
	
	def eval(y):
		# a leap year occurs...

		if y%100 == 0:
			if (y/4)%2 == 0:
				return True
			else:
				return False
		elif y%4 == 0:
			return True
		elif y%400 == 0:
			return True
		else:
			return False
