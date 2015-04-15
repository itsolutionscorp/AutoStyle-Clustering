def is_leap_year(year):
    if year % 4 != 0:
        result = False
    elif year % 100 == 0 and year % 400 != 0:
        result = False
    else:
        result = True

    return result
