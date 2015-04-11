# coding: utf-8
# from calendar import isleap (If I can use standard library)
def is_leap_year(year):
    """Examine whether the leap year.
    """
    try:
        divisible_400 = (year % 400) == 0
        divisible_100 = (year % 100) == 0
        divisible_4 = (year % 4) == 0

        if divisible_100:
            if divisible_400:
                return True
            else:
                return False
        elif divisible_4:
            return True
        else:
            return False

    except AttributeError:
        print("Error: Please input int object!")
