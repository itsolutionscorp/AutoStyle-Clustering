def distance(strand, mut_strand):
    '''This function calculates the hamming distance between two DNA strands'''

    strand_len = len(strand)
    if strand_len != len(mut_strand):
        raise ValueError('Both DNA strands need to be the same length')
    # We calculate the Hamming distance, by generating a list of "True/False"
    # entries comparing the nucleotides in the strand and mut_strand variables.
    # True where they do not match, False where they do. The sum of that list
    # is the Hamming distance.
    return sum([strand[_c] != mut_strand[_c] for _c in range(0, strand_len)])
