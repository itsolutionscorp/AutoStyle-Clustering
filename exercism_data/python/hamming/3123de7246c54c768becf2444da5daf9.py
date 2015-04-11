"""
hamming.py: Calculate the Hamming difference between two DNA strands.
"""
from itertools import zip_longest


def distance(strand_one, strand_two):
    """
    Compare two DNA strands and return count of differences.
        strand_one: First strand as a string
        strand_two: Second strand as a string
    """
    # counter starts at zero
    count = 0
    # use zip_longest to account for length differences between strands
    for org, comp in zip_longest(strand_one, strand_two):
        # if the first doesn't match the second, add to the counter
        if org != comp:
            count += 1
    return count
