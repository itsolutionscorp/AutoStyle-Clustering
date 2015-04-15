'''year.py
	created 23 Sept 2014
	by @jestuber
'''


def is_leap_year(year):
	'''leap years occur:
	on every year that is evenly divisible by 4
	except every year that is evenly divisible by 100
    unless the year is also evenly divisible by 400
    '''


	#check if we have a year
	try:
		val = int(year) 
	except ValueError:
		print("Please enter an integer year")

	if year%400 == 0:
			return True
	elif year%100 == 0:
			return False
	elif year%4 == 0:
			return True
	else:
		return False
