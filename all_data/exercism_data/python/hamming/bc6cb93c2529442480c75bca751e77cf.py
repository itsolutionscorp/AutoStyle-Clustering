"""
Hamming module
"""

def distance(nucleo1, nucleo2):
    """
    Hamming distance
    """

    diff_count = 0

    for index in range(len(nucleo1)):
        if nucleo1[index] != nucleo2[index]:
            diff_count += 1

    return diff_count

# distance()
