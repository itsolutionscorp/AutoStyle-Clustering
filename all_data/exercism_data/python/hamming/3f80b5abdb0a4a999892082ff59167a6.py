__author__ = 'jeffmarkey'

import math

def hamming(first, second):

    first_split = list(first)
    second_split = list(second)
    short_length = 0
    if (len(first_split)<len(second_split)):
        short_length = len(first_split)
    else:
        short_length = len(second_split)

    ham = math.fabs(len(first_split)-len(second_split))

    for counter in range(0, short_length):
        if (first_split[counter] != second_split[counter]):
            ham = ham + 1

    return ham
