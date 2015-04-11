def is_leap_year(year):
    if year % 400 is 0 or (year % 4 is 0 and year % 100):
        return True
    return False
