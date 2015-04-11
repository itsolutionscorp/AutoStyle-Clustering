def is_leap_year(year):
    '''
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            return False
        return True
    return False
    '''
    if year % 400 == 0:
        return True
    if year % 4 == 0 and not year % 100 == 0:
        return True
    return False



    
   # return (year % 4 == 0) or (not year % 100 == 0 or year % 400 == 0)

def is_century(year):
    return year % 100 == 0

def is_century_exeception(year):
    return year % 100 == 0 and year % 400 == 0
