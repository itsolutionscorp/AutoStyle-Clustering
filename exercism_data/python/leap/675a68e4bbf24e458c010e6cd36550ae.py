def isDiv(number, divisor):
	return number % divisor == 0

def is_leap_year(number):
	return isDiv(number, 4) and not ( isDiv(number, 100) and not isDiv(number, 400)) 
