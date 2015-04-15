from __future__ import division, print_function, unicode_literals

def is_leap_year(year):
    try:
        if not year:
            return "ERROR"
        elif (year%4) == 0:
            if (year%400) == 0:
                return True
            elif (year%100) == 0:
                return False
            return True
        else:
            return False
    except TypeError as e:
        return "ERROR"
    except AttributeError as e:
        return "ERROR"
