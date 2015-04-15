def is_leap_year(year):
    if year % 400:
        if not year % 100:
            return False
        if year % 4:
            return False
    return True
