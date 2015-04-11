# Bridgette Eichelberger
# Leap Year
# 9/23/2014


def is_leap_year(year):
    
    # any year evenly divisible by 4 is a leap year
    # but if a year is divisible by 100, only a leap year if also divisible by 400

    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            else:
                return False
        return True
    return False

