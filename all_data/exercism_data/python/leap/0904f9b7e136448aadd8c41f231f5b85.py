#

def is_leap_year(year):
    '''
    program that will take a year and report if it is a leap year.
    :param year: Positive Integer
    :return: True or False
    '''
    # check for negative value
    if year < 1:
        return False

    elif year%400 == 0:
        return True

    elif year%100 == 0:
        return False

    elif year%4 == 0:
        return True

    else:
        return False
