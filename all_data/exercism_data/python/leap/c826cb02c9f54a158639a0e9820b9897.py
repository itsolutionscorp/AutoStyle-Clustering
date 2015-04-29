def is_leap_year(year):
    year = int(year)
    if year % 4 != 0:
        return False
    if year % 100 == 0 and year % 400 != 0:
        return False
    return True
