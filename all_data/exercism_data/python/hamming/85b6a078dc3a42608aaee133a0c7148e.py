def distance(base_string, comparison_string):
    """ Compares and returns the hamming distance of 2 strings

    """
    return sum([i != j for i, j in zip(base_string, comparison_string)])
