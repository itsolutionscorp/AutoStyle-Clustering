"""
If we are going to go through the trouble of creating a file called year.py
it would make sense to declare it as a class and define is_leap_year as a
static member but for the sake of adhering to the leap_test.py file already 
provided and also for the simplicity of the problem's definition we will 
refrain from it.
"""


def is_leap_year(year):
	res = False
	
	try:
		# If it's divisible by 400 it's also divisible by 4 and 100
		# so we put this check first to avoid clutter.
		if year%400 == 0:
			res = True
		elif year%100 == 0:
			res = False
		elif year%4 == 0:
			res = True
	except TypeError:
		# Year is not a number we will let the finally clause return False
		pass
	finally:
		return res
