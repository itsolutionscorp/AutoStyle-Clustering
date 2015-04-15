def divisible_by(divisor, modulus):
    """ Takes divisor and modulus as arguments.
    Returns True if modulus divides divisor, otherwise returns False. """
    return not divisor%modulus

def is_leap_year(arg):
    """ Implements the logic described in problem statement. """
    return divisible_by(arg, 400)\
           or (divisible_by(arg, 4) and not divisible_by(arg, 100))
