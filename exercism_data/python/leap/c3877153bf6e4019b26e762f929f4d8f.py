def is_leap_year(year):
    #year divisible by 100 but not 400
    if year % 100 == 0 and year % 400 != 0:
        return False
    #year divisible by 4
    elif year % 4 == 0:
        return True
    return False
