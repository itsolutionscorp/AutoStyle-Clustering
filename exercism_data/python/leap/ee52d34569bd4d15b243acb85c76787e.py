def is_leap_year(year):

    if is_multiple_of(100)(year):
        return is_multiple_of(400)(year)
    else: 
        return is_multiple_of(4)(year)

def is_multiple_of(n):
    return lambda x: x % n == 0
