def is_leap_year (year):
    if year%4 == 0:             # check if year is evenly divisible by 4
        if year%100 == 0:       
            if year%400 == 0:
                return True     # year is divisible by 4, 100, and 400 it is a leap year
            else:
                return False    # year is divisible by 4, 100, but not 400 it isn't a leap year
        else:   
            return True         # if year is divisisble by 4 and not 100, it is a leapyear
    else:                       # if year is not divisible by 4, it is not a leap year
        return False
                
