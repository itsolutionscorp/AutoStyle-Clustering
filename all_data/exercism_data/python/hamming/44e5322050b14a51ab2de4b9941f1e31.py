def distance(base_string, comparison_string):
    """ Compares and returns the hamming distance of 2 strings

    """
    #hamming_distance = 0
    #for letter in range(len(base_string)):
    #    hamming_distance += base_string[letter] != comparison_string[letter]

    
    #return hamming_distance
    # hamming_distance = []
    pairs = zip(base_string, comparison_string)
    # set_pairs = [set(pair) for pair in pairs]
    none_matches = [(len(set(pair))) != 1 for pair in pairs] 
    hamming_distance = sum(none_matches)
    
    return hamming_distance
