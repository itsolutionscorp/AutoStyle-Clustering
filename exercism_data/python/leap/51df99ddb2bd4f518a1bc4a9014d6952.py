def is_leap_year(year):
    if year % 100 == 0:
        if year % 400 == 0:
            return True
        return False
    if year % 4 == 0:
        return True
    return False
