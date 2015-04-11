def is_leap_year(year):
    if year is not None and year % 4 == 0:
        if year % 100 == 0 and year % 400 != 0:
            return False
        return True
    return False
