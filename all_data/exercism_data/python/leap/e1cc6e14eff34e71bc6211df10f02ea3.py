# This function returns True if the year passed in is a leap year
# Otherwise it returns False

def is_leap_year(year):
    if year % 400 == 0:
        return True

    if year % 100 == 0:
        return False

    return year % 4 == 0
