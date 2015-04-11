def is_leap_year(year):
#	Start off assuming that it is not a leap year by assigning our return value as a variable.
	result = False
	# Check to see if the year fits the initial criteria of being divisible by 4.
	if year % 4 == 0:
		# If it is divisible by 4 the result variable is set to True, else it is left at False.
		result = True

	# Check to see if the assumption of True is negated by the fact that the year is also divisible by 100.
	if year % 100 == 0:
		# If it is divisible by 100, this means that it can not be a leap year and the value of the 
		# result variableis set to False.
		result = False

	# Check to see if the one exception to the divisible by 100 rule is in effect by testing as below.
	if year % 4 == 0 and year % 100 == 0 and year % 400 == 0:
		# If it does meet these three criteria then the result variable is no longer False and the value is 
		# changed to True, else the value would remain False from the divisible by 100 test
		result = True
	# return either True or False based on the result variable part of the program that requested is_leap_year
	return result
