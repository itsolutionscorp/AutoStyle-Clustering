def is_leap_year(inputYear):
    if inputYear % 4 == 0 and inputYear % 100 != 0:     # If year is divisible by 4, but not 100, return True
        return True
    elif inputYear % 4 == 0 and inputYear % 400 == 0:   # If year is divisible by 4, and also 400, return True
        return True
    else:                                               # Otherwise, return False
        return False
