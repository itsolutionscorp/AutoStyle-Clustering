""" Exercise 2 - Leap year """

def is_leap_year(year):
    """ takes the year, returns T/F if it's a leap year """
    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    elif year % 4 == 0:
        return True
    else:
        return False
