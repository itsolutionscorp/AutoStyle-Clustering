def is_leap_year(year):
    if not year % 4:
        if year % 100:
            return True
        elif not year % 400:
            return True
    return False
