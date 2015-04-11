def is_leap_year(year):
    if year % 400 == 0:
        return True
    # Mutually exclusive conditons
    elif (year % 100 == 0) ^ (year % 4 == 0):
        return True
    else:
        return False
        
