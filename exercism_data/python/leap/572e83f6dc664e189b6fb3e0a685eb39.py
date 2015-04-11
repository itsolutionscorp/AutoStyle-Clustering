def divideByFour(year):
	if year % 4 == 0:
		return True
	else:
		return False
		
def divideByOneHundred(year):
	if year % 100 == 0:
		return True
	else:
		return False
		
def divideByFourHundred(year):
	if year % 400 == 0:
		return True
	else:
		return False
		
def is_leap_year(year):
	divisibleByFour = divideByFour(year)
	divisibleByOneHundred = divideByOneHundred(year)
	divisibleByFourHundred = divideByFourHundred(year)
	
	if (divisibleByFour and not divisibleByOneHundred) or divisibleByFourHundred:
		return True
	else:
		return False
