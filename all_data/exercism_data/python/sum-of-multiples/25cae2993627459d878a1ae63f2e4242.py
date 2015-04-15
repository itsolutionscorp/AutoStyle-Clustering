"""
sum_of_multiples.py: Finds the sum of all the multiples of 3 or 5 up to
but not including the number provided.
"""


def sum_of_multiples(number, config=None):
    """
    Function to return the sum of multiples for a given number.
    Arguments:
        number: the limiter for multiples
        config: default is 3 or 5
    """
    if not config:
        config = [3, 5]
    elif config[0] == 0:
        config = config[1:]
    return sum(x for x in range(number) if any(x % y == 0 for y in config))
