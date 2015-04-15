# -*- coding: utf-8 -*-

from __future__ import division


def difference(n):
    """
    differences(int) -> int

    Return the difference between the square of the sums and the sums of the
    squares up to n.
    """

    return square_of_sum(n) - sum_of_squares(n)


def square_of_sum(n):
    """
    square_of_sum(int) -> int

    Return the square of the sum of all integers from 0 to n >= 0.
    """

    #1 + 2 + 3 + 4 + ... + n = n (n+1) //2
    #note that the division works because n or n+1 is even
    return ((n * (n + 1)) // 2) ** 2


def sum_of_squares(n):
    """
    square_of_sum(int) -> int

    Return the sum of all integers squared from 0 to n >= 0.
    """

    #Let T_n be the sum of the integers up to n
    #Let S_n be the sum of the squares up to n
    #Let K_n be the sum of the cubes up to n

    # K_n = K_(n+1) - (n+1)**3
    #     = K_n + 3*S_n + 3*T_n + n - (n+1)**3
    #
    # <=> 3*S_n = (n+1)**3 - n - 3*T_n
    #           = n**3 + 3*n**2 + 3*n + 1 - 3/2(n**2 - 1)
    #           = n**3 + 3/2*n**2 + n/2
    # <=>   S_n = 1/3 * n**3 + 1/2 * n**2 + 1/6 * n
    #           = ((2 * n + 3) * n + 1) * n * 1/6

    return (((2 * n + 3) * n + 1) * n) // 6
