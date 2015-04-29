def is_leap_year(year):
    #We want to return true if the year is a leap year and false if the year is not a leap year. A leap year is a year which is evenly divisble by 4 except every year that is evenly divisible by 100 unless the year is also evenly divisible by 400.
    #We then have several cases to consider.
    #Case 1: year divisible by 4, divisible by 100, divisible by 400
    #Case 2: year divisible by 4, not divisible by 100
    if year % 4 == 0 and year % 400 == 0:
        return True
    elif year % 4 == 0 and year % 100 != 0:
        return True
    else:
        return False
