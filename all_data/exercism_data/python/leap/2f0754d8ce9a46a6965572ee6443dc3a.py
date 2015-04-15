def is_leap_year(yr):
    '''
    Accepts a year as input and returns True or False if it is or is not a leap year.
    '''

    # only evaluate valid input
    try:
        yr = int(yr)
    except ValueError:
        return False

    if yr % 4 != 0:
        return False
    elif yr % 400 == 0:
        return True
    elif yr % 100 == 0:
        return False
    # must be divisible by just 4
    else:
        return True
