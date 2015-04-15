def is_leap_year(year):
    """
    Return `True' if given `year' is a leap year
    otherwise return `False'.
    """
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            return False
        return True
    return False
            
    
    
