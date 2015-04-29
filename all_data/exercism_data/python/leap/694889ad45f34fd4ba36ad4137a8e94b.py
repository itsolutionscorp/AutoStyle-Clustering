#on every year that is evenly divisible by 4
#  except every year that is evenly divisible by 100
#    unless the year is also evenly divisible by 400

def is_leap_year(testval):
    if ((testval % 4 == 0) and 
            not (testval % 100 == 0 and 
                not testval % 400 == 0)):
        return True
    return False
