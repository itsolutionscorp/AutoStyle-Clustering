def divby4(arg):
    if arg % 4 == 0:
        return True

def divby100(arg):
    if arg % 100 == 0:
        return True

def divby400(arg):
    if arg % 400 == 0:
        return True



def is_leap_year(year):

    if divby4(year) and divby100(year):
        if divby100(year) and divby400(year):
            return True
    elif divby4(year):
        return True
    else:
        return False


        
