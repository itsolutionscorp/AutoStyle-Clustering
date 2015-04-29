def is_leap_year(year_num):
    if year_num % 4 == 0:
        leap = True
    else:
        return False
        
    if year_num % 100 == 0 and year_num % 400 == 0:
        return True
    elif year_num % 100 > 0:
        return True
    else:
        return False
