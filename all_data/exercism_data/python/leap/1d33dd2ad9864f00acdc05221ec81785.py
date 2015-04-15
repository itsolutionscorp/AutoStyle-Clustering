def is_leap_year(y):
    assert type(y) == int
    
    is_leap = False
    if  not y % 4: # is   leap year = True
        is_leap = True
        if not y % 100: # Exception
            is_leap = False
            if not y % 400:# Exception from Exception
                is_leap = True
    
    return is_leap
