def is_leap_year(year):
    if not (year % 100) and (year % 400):
        return False
    elif not (year % 400) or not (year % 4):
        return True
    else:
        return False
