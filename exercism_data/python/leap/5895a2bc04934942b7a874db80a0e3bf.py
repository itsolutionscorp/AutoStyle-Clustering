# -*- coding: utf-8 -*-

def is_leap_year(year):
	if (year % 400 == 0):
		return(True)
	if (year % 100 == 0):
	    return(False)
	if (year % 4 == 0):
		return(True)
	return(False)


def main():
	test = 2000
	print test
	print is_leap_year(test)


if __name__ == '__main__':
    main()	
