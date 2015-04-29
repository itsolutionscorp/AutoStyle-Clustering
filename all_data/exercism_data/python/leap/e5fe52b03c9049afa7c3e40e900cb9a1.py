def is_leap_year(year):
    if (year % 100 != 0) and (year % 4 == 0) or (year % 400 == 0):
        return True
    else:
        return False
