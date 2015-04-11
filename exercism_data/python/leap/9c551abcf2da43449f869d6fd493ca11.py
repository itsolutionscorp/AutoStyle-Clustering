
def is_leap_year( year ):

    if year % 4 == 0:

        if year % 100 == 0 and not year  % 400 == 0:
            #year is divisible by 100 but not by 400
            return False

        return True
    
    return False
