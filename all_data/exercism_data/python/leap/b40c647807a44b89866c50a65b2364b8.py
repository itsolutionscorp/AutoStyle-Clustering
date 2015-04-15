def is_leap_year(year):
    if year % 400 == 0:
        # Divisible by 4, 100 and 400
        return True
    
    if year % 100 == 0:
        # Divisible by 4 and 100 but not 400
        return False
    
    if year % 4 == 0:
        # Divisible by 4 but not by 100
        return True
    
    # Not divisble by 4
    return False
