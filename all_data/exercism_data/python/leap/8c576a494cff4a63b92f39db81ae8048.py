# A leap year occurs:
# on every year that is evenly divisible by 4
#  except every year that is evenly divisible by 100
#    unless the year is also evenly divisible by 400

def is_leap_year(year):
    if (year%4 == 0): # YES if evenly divisible by 4
        if (year%100 == 0): # EXCEPT if it's divisible by 100
            if (year%400 == 0): # UNLESS it's ALSO divisible by 400
                return True
            return False
        return True
    else:
        return False # NO for everything else
