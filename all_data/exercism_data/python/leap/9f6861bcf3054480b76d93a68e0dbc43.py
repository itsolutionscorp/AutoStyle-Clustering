def is_leap_year(year):
    if year % 4 is not 0:
        return False
    elif year % 100 is not 0:
        return True
    elif year % 400 is not 0:
        return False
    return True
