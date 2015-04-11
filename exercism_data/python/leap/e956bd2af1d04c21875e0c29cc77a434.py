def is_leap_year(x):
    y = [x/4.0]
    z = [x/100.0]
    a = [x/400.0]
    if int(str(y)[-2]) == 0:
        pass
    else:
        return False
    if int(str(z)[-2]) == 0:
        pass
    else:
        return True
    if int(str(a)[-2]) == 0:
        return True
    else:
        return False

