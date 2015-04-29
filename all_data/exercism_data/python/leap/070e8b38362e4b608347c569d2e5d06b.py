def is_leap_year(year):
    if not (year / 4).is_integer():
        return False
    if (year / 400).is_integer():
        return True
    if (year / 100).is_integer():
        return False
    return True
