def is_leap_year(y):
    if (0 == (y%4)):
        if (0 == (y%100)):
            if (0 == (y%400)):
                return True
            return False
        return True
    return False

                
            
