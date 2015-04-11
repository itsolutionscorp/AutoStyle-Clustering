# Leap
#
# exercism "leap" code challenge
# Joshua Ferdaszewski
#
# Takes a year and returns whether it is a leap year or not
# 
# on every year that is evenly divisible by 4
# except every year that is evenly divisible by 100
# unless the year is also evenly divisible by 400

def is_leap_year(year):

    year = int(year)

    if year % 4 == 0:
       return (year % 100 != 0) or (year % 400 == 0)
    else:
        return False
