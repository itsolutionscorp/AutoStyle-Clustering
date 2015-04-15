"""exercism.io - Python 'Leap' exercise solution."""

def is_leap_year(_year):
    """Returns a boolean."""

    if _year%4 == 0:
        if _year%100 == 0:
            if _year%400 == 0:
                return True
            else: return False
        return True
    else: return False
