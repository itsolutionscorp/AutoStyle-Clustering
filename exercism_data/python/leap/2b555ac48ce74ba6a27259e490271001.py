def is_leap_year(year):
''' decides if the input is a leap year or not
'''
    if year % 4 == 0:  # divisible by 4
        if year % 400 == 0: # divisible by 400, so it is a leap year
            return True
        if year % 100 == 0: # divisible by 100, no not a leap year
            return False
        return True # just a plain leap year / fallthrough
    else: # not divisible by 4 
        return False
