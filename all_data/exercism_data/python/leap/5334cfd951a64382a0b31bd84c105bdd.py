# the modern calender, with leap years, tracks the position of the earth
# to within one day per 8000 years

# determine if a given year is a leap year
def is_leap_year(the_year):
	# a year is a leap year if it is
	# divisible by 4,
	# except when it is also divisible by 100,
	# unless it is divisible by 400
	if (divisible_by(the_year,400)):
		return True
	elif (divisible_by(the_year,100)):
		return False
	elif (divisible_by(the_year,4)):
		return True

	return False

# check if the dividend can by divided into pieces
def divisible_by(dividend, divisor):
	return (dividend%divisor == 0)
