def is_leap_year(y):
    if isdiv4(y):
        if isdiv100(y):
            if isdiv400(y):
                return True
            else:
                return False
        else:
            return True
    else:
        return False


def isdiv4(y):
    return y % 4 == 0


def isdiv100(y):
    return y % 100 == 0


def isdiv400(y):
    return y % 400 == 0
