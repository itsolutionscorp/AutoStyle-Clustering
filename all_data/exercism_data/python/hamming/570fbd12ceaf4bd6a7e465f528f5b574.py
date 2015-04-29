def hamming(a_strand, b_strand):
    '''
    hamming(string, string) -> integer
    Computes Hamming Distance between two DNA strands.
    DNA string consists only of letters ATGC
    '''
    min_strand = len(min(a_strand,b_strand,key=len))
    max_strand = len(max(a_strand,b_strand,key=len))
    Hamming_dist = max_strand - min_strand
    for i in range(min_strand):        
        if a_strand[i] != b_strand[i]:
            Hamming_dist += 1
    return Hamming_dist
