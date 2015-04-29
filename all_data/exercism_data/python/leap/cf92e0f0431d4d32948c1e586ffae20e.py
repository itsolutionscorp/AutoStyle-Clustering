""" test if a year is leap or not and return a boolean """


def is_leap_year(year):
	result=False # the function will return the variable result, which is set to False by default we will set to true in appropriate case
	if ((year % 4 == 0) and (year % 100) != 0): # the year is divisible by 4 not by 100
		result=True
	elif (year % 400 == 0): # the year is divisible by 400
		result=True

	return result
