def is_leap_year(year):

    if year % 4 == 0:           # Leap year is divisible by 4...
        if year % 100 != 0:     # ...but not by 100...
            return True
        elif year % 400 == 0:   # ...unless it is divisible by 400
            return True
    return False
