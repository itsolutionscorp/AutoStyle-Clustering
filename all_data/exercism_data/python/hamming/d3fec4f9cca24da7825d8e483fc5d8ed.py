def hamming(strand1, strand2):
    # Get the case where they are the same out of the way early for efficiency.
    if strand1 == strand2:
        return 0

    # Get the lengths of the strands
    s1_length, s2_length = len(strand1), len(strand2)

    # Initialize hamming value as the difference in length between strand1 and
    # strand2.
    hamming_val = abs(s1_length - s2_length)

    for nucleotide in range(min(s1_length, s2_length)):
        # int(True) is 1, int(False) is 0
        hamming_val += int(strand1[nucleotide] != strand2[nucleotide])

    return hamming_val
