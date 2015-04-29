def is_leap_year(year):
    if year % 4 == 0:
        if not year % 100 == 0 and not year % 400:
            return False
        else:
            return True
    else:
        return True
