##
## on every year that is evenly divisible by 4
## except every year that is evenly divisible by 100
## unless the year is also evenly divisible by 400

def is_leap_year(year = -1):       
    value = False
    if isinstance(year, int) and year > 0:
        if ( year % 4 == 0 ):
            value = True
            if ( year % 100 == 0 ):
                value = False
                if ( year % 400 == 0 ):
                    value = True
    return value
            
