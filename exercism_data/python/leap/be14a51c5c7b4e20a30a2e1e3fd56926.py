def divisible_by(divisor, modulus):
    """ Takes divisor and modulus as arguments.
    Returns True if modulus divides divisor, otherwise returns False. """
    return not divisor % modulus

def is_leap_year(arg):
    """ Implements the logic described in problem statement. """
    by_4_not_100 = divisible_by(arg, 4) and not divisible_by(arg, 100)
    by_400 = divisible_by(arg, 400) 
    return any([by_4_not_100, by_400])
