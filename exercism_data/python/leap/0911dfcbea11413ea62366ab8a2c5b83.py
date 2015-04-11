#
# Is it a leap year?
#
def is_leap_year(year):
    if year % 100 == 0:
        if year % 400 == 0:
            return True
        else:
            return False
    return year % 4 == 0
    
