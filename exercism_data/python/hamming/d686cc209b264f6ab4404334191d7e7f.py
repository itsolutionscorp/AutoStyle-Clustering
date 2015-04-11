def hamming(strand_1, strand_2):
    # Count the number of differences in the strands
    extra_errors = max(len(strand_1), len(strand_2)) - min(len(strand_1), len(strand_2))
    return sum([(a != b) for a, b in zip(strand_1, strand_2)]) + extra_errors
