# Check, if it is a leap year.
# A leap year occurs:
# on every year that is evenly divisible by 4
#  except every year that is evenly divisible by 100
#    unless the year is also evenly divisible by 400


def is_leap_year(year):
    
    is_multiple_of_4 = year % 4 == 0
    is_multiple_of_100 = year % 100 == 0
    is_multiple_of_400 = year % 400 == 0

    #if year % 4 == 0 and (year % 100 != 0  or year % 400 == 0):    
    if is_multiple_of_4 and (not is_multiple_of_100 or is_multiple_of_400):
        leap = True
    else:
        leap = False

    return leap
