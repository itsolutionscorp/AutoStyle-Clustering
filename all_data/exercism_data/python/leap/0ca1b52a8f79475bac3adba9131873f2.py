#
# Leap Year Detector for Python "Leap" exercise
#

def is_leap_year(year):

# set default return value
    return_val = False

# if year evenly divides by 4
    if (year % 4) == 0:
# if year evenly divides by 100 it isn't a leap year
        if (year % 100) == 0:
# unless evenly divisible by 400
            if (year % 400) == 0:
                return_val = True
        else:
            return_val = True

    return return_val
