def is_leap_year(year):
    # Divisible by 400 (leap year)
    if year % 400 == 0:
        return True
        
    # Divisible by 100 (not leap year)
    if year % 100 == 0:
        return False
        
    # Divisible by 4 (leap year)
    if year % 4 == 0:
        return True
