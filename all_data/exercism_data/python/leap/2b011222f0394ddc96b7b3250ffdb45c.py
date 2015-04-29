def is_leap_year(year):
    '''returns: boolean, if year is a leap year
       year: int
    '''
    if (year % 4 == 0):
        if (year % 100 == 0):
            if (year % 400 == 0):
                return True
            else:
                return False
        else:
            return True
    else:
        return False
