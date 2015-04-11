def is_leap_year(year):
    
    div_400 = (year % 400.0)  #produces remainder as a floating point
    div_100 = (year % 100.0)
    div_4 = (year % 4.0)

    if div_400 == 0:
        return True
    elif div_100 == 0:
        return False
    elif div_4 == 0:
        return True
    else:
        return False
