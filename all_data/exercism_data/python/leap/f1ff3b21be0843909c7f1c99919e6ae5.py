""" Exercise 2 - Leap year """

def is_leap_year(year):
    """ takes the year, returns T/F if it's a leap year """
    return (year % 4 == 0) and ((year % 100 != 0) or (year % 400 == 0))
