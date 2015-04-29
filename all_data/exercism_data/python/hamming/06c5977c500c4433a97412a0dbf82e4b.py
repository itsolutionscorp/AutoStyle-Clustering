def distance(base_strand, comparison_strand):
    """Compares and returns the hamming distance of 2 DNA strands

    """
    hamming_distance = 0

    for nucleotide in range(len(base_strand)):
        if base_strand[nucleotide] != comparison_strand[nucleotide]:
            hamming_distance += 1
    
    return hamming_distance
