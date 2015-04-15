def is_leap_year(this_year):
    if this_year % 400 == 0 or this_year % 4 == 0 and this_year % 100 != 0:
        return True
    else:
        return False
