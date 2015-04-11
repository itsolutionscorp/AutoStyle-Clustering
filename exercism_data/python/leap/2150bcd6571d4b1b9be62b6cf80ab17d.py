def divisible_by(divisor, modulus):
    """ Takes divisor and modulus as arguments.
    Returns True if modulus divides divisor, otherwise returns False. """
    return not divisor % modulus

def is_leap_year(arg):
    """ Implements the logic described in problem statement. """
    return any([divisible_by(arg, 400), 
                all([divisible_by(arg, 4), not divisible_by(arg, 100)])])
