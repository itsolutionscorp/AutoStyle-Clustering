def hamming(a_strand, b_strand):
    min_strand_length = min(len(a_strand),len(b_strand))
    max_strand_length = max(len(a_strand),len(b_strand))
    Hamming_dist = max_strand_length - min_strand_length
    for i in range(0,min_strand_length):
        if a_strand[i] != b_strand[i]:
            Hamming_dist += 1
    return Hamming_dist
