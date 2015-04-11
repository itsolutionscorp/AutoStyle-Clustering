def is_leap_year(year):
    if year != int(year):
        raise ValueError("The year %f is not an integer." % year)
        
    if (year % 400) == 0:
        return True
        
    if (year % 100) == 0:
        return False
        
    if (year % 4) == 0:
        return True
        
    return False
    
    
