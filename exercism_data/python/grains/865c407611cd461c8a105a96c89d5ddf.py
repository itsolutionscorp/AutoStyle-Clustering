__author__ = 'emiller42'


def on_square(square):
    """
    So while we could do math here, I'm lazy.
    The puzzle is essentially counting in base 2 (binary)
    Square 1 =    1 =          1
    Square 2 =    2 =         10
    Square 3 =    4 =        100
    ...
    Square 10 = 512 = 1000000000
    etc.

    So we don't need to do any math!
    For square n, just make a binary string
    of '1' followed by n-1 '0's then
    convert to an int!
    """
    binary = '1' + '0' * (square - 1)
    return int(binary, 2)


def total_after(square):
    """
    Perhaps you can see where we're going here.
    Based on the above...
    Square 1 = 1 =   1
    Square 2 = 2 =  10
    Square 3 = 4 = 100

    Then the sum of the above is simply
    111 = 4 + 2 + 1 = 7

    So getting a total of grains up to and including square n
    means simply constructing a binary string of n '1's
    and converting it to an int.

    No math, no loops, no mess!
    """
    binary = '1' * square
    return int(binary, 2)
