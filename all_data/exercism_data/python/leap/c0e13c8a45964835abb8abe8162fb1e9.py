def is_leap_year(year):
    ''' takes year as input and returns True if year is leap year
        or False if not '''

    # leap year must be divisible by 4
    if year % 4 == 0:
        # if it is divisible by 100, then it  is a leap year only if divisible 
        # by 400
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            else:
                return False
        return True

    # the year isn't divisible by 4
    return False
