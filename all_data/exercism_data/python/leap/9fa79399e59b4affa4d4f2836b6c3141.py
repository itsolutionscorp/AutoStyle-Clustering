'''year.py
	created 23 Sept 2014
	by @jestuber
'''

def is_leap_year(year):
	#check if we have a year
	try:
		val = int(year) 
	except ValueError:
		print("Please enter an integer year")

	if year%4 == 0:
		if year%400 == 0:
			return True
		if year%100 == 0:
			return False
		else:
			return True
	else:
		return False
