def is_leap_year(year):
    is_leap=False
    if year%4 == 0:
        is_leap = True
    if not year%100 and is_leap:
        is_leap = False
        if not year%400:
            is_leap = True
    return is_leap
    
