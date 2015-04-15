def is_leap_year(year):

#   A year is a leap year on every year that is evenly divisible by 4
#   except every year that is evenly divisible by 100
#   unless the year is also evenly divisible by 400

    if year % 400 == 0:
        return True
    if year % 100 == 0:
        return False
    if year % 4 == 0:
        return True

    else:
        return False
    
