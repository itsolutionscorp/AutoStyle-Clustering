def is_leap_year(y):
    """Returns True :
        if y % 4 == 0 and y % 100 != 0
        or if y % 400 == 0
        returns False otherwise
        """
    assert type(y) == int  # make sure the argument can be treated as a year

    # helper function to detect if a is evenly divisible by b
    divisible = lambda a,b: not bool(a%b)  # returns True if a = k * b

    return divisible(y, 400) or ( divisible(y, 4) and not divisible(y, 100) )
