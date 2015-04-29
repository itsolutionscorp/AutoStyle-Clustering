"""
hamming - a module for Hamming distance calculations.
"""

def distance(a, b):
    """
    Calculate the Hamming distance between two DNA sequences.
    """

    # Sum all the differences via a generator expression.
    return sum(1 for i, j in zip(a, b) if i != j)
