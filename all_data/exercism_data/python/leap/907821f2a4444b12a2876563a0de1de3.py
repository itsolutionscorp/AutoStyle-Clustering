# -*- coding: utf-8 -*-

def is_leap_year(year):
	#"""
	if (year % 4 != 0):
		return(False)
	if (year % 400 == 0):
		return(True)
	if (year % 100 == 0):
		return(False)
	return(True)
	# """    
	"""
	if (year % 4 != 0) or (year % 400 != 0 and year % 100 == 0):	
		return(False)
	return(True)	
	"""
	# return year % 4 == 0 and year % 100 != 0 or year % 400 == 0

def main():
	test = 1900
	print test
	print is_leap_year(test)


if __name__ == '__main__':
    main()	
