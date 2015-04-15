def hamming(strand_a, strand_b):

    # Create a list of tuples, with each
    # tuble containing chars from each string.
    # Add one if the elements of each tuple are different
    # and finally count all the ones.

    hamming_distance = sum([ 1 for a, b in zip(strand_a, strand_b) if a != b ])

    # If the strings are not equal, add the difference.

    if len(strand_b) != len(strand_a):
        hamming_distance += abs(len(strand_b) - len(strand_a))

    return hamming_distance
