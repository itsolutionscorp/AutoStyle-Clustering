##  Leap Year Test

def is_leap_year(year):
    ## If a year is divisible by 100, but not 400, it's not a leap year
    if int(year) % 100 == 0 and int(year) % 400 != 0:
       return False

    ## Otherwise, if a year is divisible by 4, it's a leap year
    if int(year) % 4 == 0:
        return True

    ## In all other cases, it's not a leap year
    else:
        return False
