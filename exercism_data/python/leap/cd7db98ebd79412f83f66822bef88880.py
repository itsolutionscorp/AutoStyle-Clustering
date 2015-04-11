def is_leap_year(year):
    is_divisibly_by = lambda x,y : x % y == 0

    # years divisble by 400 are always leap years
    if is_divisibly_by(year, 400):
        return True
    # years divisble by 100 are never leap years
    elif is_divisibly_by(year, 100):
        return False
    # all other years divisible by 4 are leap years
    elif is_divisibly_by(year, 4):
        return True
    else:
        return False
