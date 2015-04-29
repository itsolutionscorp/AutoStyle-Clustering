def is_leap_year(year):
    #leap years must be multiples of 4
    if year % 4 == 0:
        #if they are multiples of 100 they must also be multiples of 400
        if year % 100 == 0:
            return year % 400 == 0            
        return True
    return False
