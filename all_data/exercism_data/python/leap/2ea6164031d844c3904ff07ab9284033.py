def is_leap_year(year):
    if year % 4 == 0:
        if year % 100 is 0 and year % 400 is not 0:
            return False
        elif year % 100 is 0 and year % 400 is 0:
            return True
        return True
    else:
        return False
