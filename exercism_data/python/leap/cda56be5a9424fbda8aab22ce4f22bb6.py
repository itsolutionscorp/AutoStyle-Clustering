# A test for if a year is a leap year

def is_leap_year(year):
    """Tests if a given year is a leap year. Namely if a year is:
    -a leap year if it's divisible by 4
    -not a leap year if it's also divisible by 100
    -a leap year if it's also divisible by 400
    
    """
    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    elif year % 4 == 0:
        return True
    else:
        return False
