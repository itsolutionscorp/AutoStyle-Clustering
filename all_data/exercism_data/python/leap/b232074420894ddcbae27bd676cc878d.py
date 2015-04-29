def is_leap_year(year):

    if year % 400 == 0: # year is evenly divisible by 400
        return True

    elif year % 100 == 0: # year is evenly divisible by 100
        return False

    elif year % 4 == 0: # year is evenly divisible by 4
        return True

    else: # Default Case - not a leap year
        return False
