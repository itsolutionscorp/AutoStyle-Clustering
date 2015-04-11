# every four year, but not on the 100th year, but yes on the 400th year

def is_leap_year(year):
    if year % 4 == 0 and year % 100 != 0:
        return True
    elif year % 400 == 0:
        return True 
    else:
        return False


