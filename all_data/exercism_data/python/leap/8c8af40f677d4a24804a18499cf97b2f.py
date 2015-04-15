def is_leap_year(year):
    if year % 4 != 0:
        return False

    # If both 4 and 400 divide the year then it's certainly a leap year.
    if year % 400 == 0:
        return True

    # Since we ruled out the edge case where 4, 100 and 400 divide the 
    # year, we can conclude here that it's not a leap year.
    if year % 100 == 0:
        return False

    return True
