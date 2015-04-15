"""
Hamming module
"""

def distance(nucleo1, nucleo2):
    """
    Hamming distance
    """

    diff_count = 0

    for n1, n2 in zip(nucleo1, nucleo2):
        if n1 != n2:
            diff_count += 1

    return diff_count

# distance()
