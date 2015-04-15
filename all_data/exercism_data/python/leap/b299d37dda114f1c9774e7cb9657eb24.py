# Leap Exercise 
# Excercism Python Exercise #2
# Iteration 1

# is_leap_year takes a four digit integer argument (year)
def is_leap_year(year):
	if year % 400 == 0: return True
	if year % 100 == 0: return False
	if year % 4 == 0: return True
	else: return False
