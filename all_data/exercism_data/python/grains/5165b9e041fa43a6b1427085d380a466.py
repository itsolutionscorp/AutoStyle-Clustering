__author__ = 'emiller42'


def on_square(square):
    """
    start with 1 and shift bits left n - 1 times.
    New digits on the right are zeros

    n = 5
    1 << (5 - 1) = 10000 = 16
    equivalent to 2 ** n  (2 ** 5 = 16)
    """
    return 1 << (square - 1)


def total_after(square):
    """
    start with 1 and shift bit left n times, then
    subtract 1.

    n = 5
    1 << 4 = 100000 = 32
       - 1 =  11111 = 31

    1 + 2 + 4 + 8 + 16 = 31
    """
    return (1 << square) - 1
