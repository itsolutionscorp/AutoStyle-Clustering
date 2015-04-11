

def is_leap_year(year):
	
	def divisible_by (n): return year%n == 0

	if divisible_by(400):
		return True
	elif divisible_by(100):
		return False
	elif divisible_by(4):
		return True
	else:
		return False
