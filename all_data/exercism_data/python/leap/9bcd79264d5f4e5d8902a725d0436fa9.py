def is_leap_year(year):
    if (year % 4) or (year % 400) and not (year % 100):
        return 0
    else:
        return 1
        
