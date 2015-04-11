# Hanley Wasington's attempt at the "leap" exercism in python

def test_leap_year(y):
    if y % 4 != 0:
        return False
    elif y % 100 == 0:
        if y % 400 == 0:
            return True
        else:
            return False
    else:
        return True
