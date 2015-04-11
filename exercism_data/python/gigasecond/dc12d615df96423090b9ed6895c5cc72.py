#!/usr/bin/python -tt

from datetime import date
#Without use of datetime timedelta and other advanced features

def add_gigasecond(iDate):
	
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
	
	def incMonth(month,year):
	#Returns month,year incremented by 1 month
		month+=1
		if month > 12:
			month+=-12
			year+=1
		return month,year
	
	cYear = iDate.year
	cMonth = iDate.month
	cDay = iDate.day
	
	sInDay = 24*60*60
	dCount = 1000000000/sInDay #Number of days in 1 billion seconds
	
	#Go to 1st of next month
	dCount+= -(dInMonth(cMonth,cYear)-cDay+1)
	cMonth,cYear = incMonth(cMonth,cYear)
	cDay = 1
	
	#Increase year counter while dCount > 1 year
	while dCount > 365:
		if is_leap_year(cYear):
			dCount+= -366
		else:
			dCount+= -365
		cYear+=1
	
	#If output year is leap year and february is past, add a day to dCount
	if is_leap_year(cYear) and cMonth>2:
		dCount+= -1
	
	#Increment month while dCount > 1 month
	while dCount > dInMonth(cMonth,cYear)-1:
		dCount+= -(dInMonth(cMonth,cYear))
		cMonth,cYear = incMonth(cMonth,cYear)

	#Add remaining days
	cDay+=dCount
	
	return date(cYear,cMonth,cDay)
	
if __name__ == "__main__":
  dateA = date(2011, 4, 25)
  dateB = add_gigasecond(dateA)
  print dateB
