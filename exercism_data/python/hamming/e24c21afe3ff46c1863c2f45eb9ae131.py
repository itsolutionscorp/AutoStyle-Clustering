def distance(strand, mut_strand):
    '''This function calculates the hamming distance between two DNA strands'''

    strand_len = len(strand)
    if strand_len != len(mut_strand):
        raise ValueError('Both DNA strands need to be the same length')
    return sum([strand[c] != mut_strand[c] for c in range(0, strand_len)])
    
