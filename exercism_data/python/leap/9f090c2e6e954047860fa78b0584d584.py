def is_leap_year(year):
    if not year % 4 and year % 100:
        return True
    elif year % 400 == 0:
        return True
    return False
    
