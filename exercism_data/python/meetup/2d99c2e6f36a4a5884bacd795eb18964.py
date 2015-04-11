#!/usr/bin/python -tt

from datetime import date

def meetup_day(year, month, dOfWeek, pos):

	def is_leap_year(year):
	#Determines if year is a leap year
		if year % 400 == 0:
			return True
		if year % 100 == 0:
			return False
		if year % 4 == 0:
			return True
		return False

	def dInMonth(month,year): 
	#Number of days in the month
		if month in [1,3,5,7,8,10,12]:
			return 31
		elif month in [4,6,9,11]:
			return 30
		elif month == 2:
			if is_leap_year(year):
				return 29
			else:
				return 28
		else:
			raise ValueError('Incorrect Month')
	
	def numWeekDay(dOfWeek):
	#Parse day of week
		if dOfWeek == 'Monday':
			return 0
		if dOfWeek == 'Tuesday':
			return 1
		if dOfWeek == 'Wednesday':
			return 2
		if dOfWeek == 'Thursday':
			return 3
		if dOfWeek == 'Friday':
			return 4
		if dOfWeek == 'Saturday':
			return 5
		if dOfWeek == 'Sunday':
			return 6
			
	def numPos(pos):
	#Parse position in month
		if pos == '1st':
			return 1
		if pos == '2nd':
			return 2
		if pos == '3rd':
			return 3
		if pos == '4th':
			return 4
		if pos == 'last' or pos == 'teenth':
			return -1
			
	numDayOfWeek=numWeekDay(dOfWeek)
	tarPos = numPos(pos)

	if numPos(pos) > 0:
	#Parse input
		cDayOfWeek = date(year, month, 1).weekday()
		cDay = 1
		cPos = 0
		if cDayOfWeek == numDayOfWeek:
			cPos = 1
		while not (cDayOfWeek == numDayOfWeek and cPos == tarPos):
			cDay += 1
			cDayOfWeek = date(year, month, cDay).weekday()
			if numDayOfWeek == cDayOfWeek:
				cPos+=1
				
	elif pos == 'last':
		cDayOfWeek = date(year, month, dInMonth(month,year)).weekday()
		cDay = dInMonth(month,year)
		while not (cDayOfWeek == numDayOfWeek):
			cDay+= -1
			cDayOfWeek = date(year, month, cDay).weekday()
			
	elif pos == 'teenth':
		for n in [13,14,15,16,17,18,19]:
			cDayOfWeek = date(year, month, n).weekday()
			if cDayOfWeek == numDayOfWeek:
				cDay = n
				break
				
	return date(year, month, cDay)
			
if __name__ == '__main__':
	print meetup_day(2013, 3, 'Sunday', '4th')
