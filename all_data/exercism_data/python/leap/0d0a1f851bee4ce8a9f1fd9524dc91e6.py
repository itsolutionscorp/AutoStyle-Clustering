def is_leap_year(year):
    if year % 4 == 0: # if divisible by 4
        if year % 100 == 0: # and by 100
            if year % 400 == 0: # and by 400
                return True
            return False
        return True
