def hamming(seq1,seq2):

    # Get the lengths of the shorter and longer set to determine comparison range.
    longerSeq = len(max(seq1,seq2,key=len))
    shorterSeq = len(min(seq1,seq2,key=len))

    # Initialize the hamming difference to be the difference in length between the two sequences.
    diffCount = longerSeq - shorterSeq

    # Compare the nucleotides for the calculated range.
    for nucleotide in range(0,shorterSeq):
        if (seq1[nucleotide] != seq2[nucleotide]):
            diffCount += 1
    return diffCount
