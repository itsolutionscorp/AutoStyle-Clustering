def is_leap_year(year):
    if year % 100:
        return False if year % 4 else True
    return False if year % 400 else True
