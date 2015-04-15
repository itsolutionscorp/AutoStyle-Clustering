def is_leap_year(year):
    '''
    Returns true if year is a leap year
    and false otherwise
    '''
    if year % 400 == 0:
        return True
    if year % 100 == 0:
        return False
    if year % 4 == 0:
        return True
    return False
