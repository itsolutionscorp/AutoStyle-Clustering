def divby4(arg):
    return arg % 4 == 0

def divby100(arg):
    return arg % 100 == 0

def divby400(arg):
    return arg % 400 == 0


def is_leap_year(year):
    if divby400(year):
        return True
    elif divby4(year) and not divby100(year):
        return True
    else:
        return False


        
