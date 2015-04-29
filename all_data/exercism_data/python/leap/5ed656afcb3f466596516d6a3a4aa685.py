def is_leap_year(year):
    if year % 4:
        result = False
    elif not year % 100 and year % 400:
        result = False
    else:
        result = True
    
    return result
