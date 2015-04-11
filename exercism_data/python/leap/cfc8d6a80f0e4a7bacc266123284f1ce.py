# coding: utf-8
# from calendar import isleap (If I can use standard library)
def is_leap_year(year):
    """Examine whether the leap year.
    """
    try:
        if (((year % 100) == 0) and ((year % 400) != 0)):
            return False
        elif (year % 4 == 0):
            return True
        else:
            return False
    except TypeError:
        print("Error: Please input Integer!")
