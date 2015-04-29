def is_leap_year(input):
    """
    input: year to test
    returns whether or not input is a leap year
    """

    if (not str(type(input)) == "<type 'int'>"):
        raise Exception("Input must be an integer")

    if (input % 4 == 0 and input % 100 != 0):
        return True
    elif (input % 400 == 0):
        return True
    else:
        return False
