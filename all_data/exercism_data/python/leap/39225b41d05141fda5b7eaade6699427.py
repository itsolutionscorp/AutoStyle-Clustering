def is_leap_year(year):
    if year % 4 == 0 and (year % 100 or year % 400 == 0):
        return True
    return False
