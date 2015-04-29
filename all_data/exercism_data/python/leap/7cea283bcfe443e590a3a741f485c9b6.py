def is_leap_year(year):
    if not year % 400:
        return True
    if not year % 100 or year % 4 != 0:
        return False
    else:
        return True
