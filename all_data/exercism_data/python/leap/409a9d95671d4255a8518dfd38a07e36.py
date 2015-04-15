def is_leap_year(year):
    '''year must be an integer greater than 0
    return True if year is a leap year and
    False otherwise'''
    
    assert type(year)==type(0)
    assert year > 0

    if year % 4 == 0:
        if year % 400 == 0:
            return True

        elif year % 100 == 0:
            return False

        else:
            return True

    elif year % 4 != 0:
        return False
