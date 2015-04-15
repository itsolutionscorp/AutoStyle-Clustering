def hamming(strand1, strand2):
    """Returns hamming distance between two DNA strands"""

    # Function returns True when arguments (bases) do not match
    def compare(x, y): return x != y

    # Return difference between strand-lengths + number of point mutations
    return abs(len(strand1) - len(strand2)) +  list(map(compare, strand1, strand2)).count(True)
    
