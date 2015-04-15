def is_leap_year(year):
    if year % 4 == 0:
        if year % 400 == 0 or year % 100 != 0:
            return True
