def is_leap_year(year) :
    if year % 4 is 0 and not year % 100 is 0:
        return True
    elif year % 400 is 0:
        return True
    else:
        return False
