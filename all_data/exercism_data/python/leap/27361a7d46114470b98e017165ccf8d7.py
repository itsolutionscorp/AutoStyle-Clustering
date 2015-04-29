# leap.py

def is_leap_year(year):
    if year%4 == 0:
        if year%100 == 0:
            if year%400 == 0:
                result = True
            else:
                result = False
        else:
            result = True
    else:
        result = False
    
    return result
