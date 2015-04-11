def hamming(a_strand, b_strand):
    '''
    hamming(string, string) -> integer
    Computes Hamming Distance between two DNA strands.
    DNA string consists only of letters ATGC
    '''
    strands = zip(a_strand,b_strand)
    return (
        abs(len(a_strand)-len(b_strand))+
        len([x for x,y in strands if x != y]))
