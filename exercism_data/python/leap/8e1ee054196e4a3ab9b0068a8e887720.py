def is_leap_year(year):
    if not year % 100 and year % 400:
        return False
    elif not year % 4:
        return True
    return False
