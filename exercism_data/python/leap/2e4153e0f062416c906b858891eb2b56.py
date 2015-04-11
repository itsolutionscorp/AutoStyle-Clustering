#checks for leap year

def is_leap_year(year):

    #check specific cases
    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False

    #check general case
    elif year % 4 == 0:
        return True

    #default 
    return False
