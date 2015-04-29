def is_leap_year(year):
    
    if year%4 != 0:
        return False #Only years that are divisible by 4 are leap years
    
    elif year%100 == 0:
    
        if year%400 == 0:
            return True #Including those that are multiples of 100
        
        else:
            return False #But otherwise, not

    return True 
