def is_leap_year(year):
    # if the year is divisible by 400, it is a leap year
    if year % 400 == 0:
        return True

    # if the year isn't divisible by 400 (already tested), 
    # but *is* divisible by 100, it is not a leap year.
    elif year % 100 == 0:
        return False

    # if the year is divisible by 4 and not caught by earlier tests,
    # it is a leap year
    elif year % 4 == 0:
        return True

    # other years are not leap years    
    return False
