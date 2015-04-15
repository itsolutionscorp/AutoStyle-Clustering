def is_leap_year(year):
    if abs(year - 1600) % 4:
        return 0
    else:
        return 1
        
