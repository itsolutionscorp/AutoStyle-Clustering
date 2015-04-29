def distance(base_string, comparison_string):
    """Compares and returns the hamming distance of 2 strings

    """
    hamming_distance = 0
    for letter in range(len(base_string)):
        hamming_distance += base_string[letter] != comparison_string[letter]
    return hamming_distance
