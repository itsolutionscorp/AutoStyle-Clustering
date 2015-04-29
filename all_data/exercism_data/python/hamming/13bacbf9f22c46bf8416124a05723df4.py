def hamming(strand1, strand2):
    # Get the case where they are the same out of the way early for efficiency.
    if strand1 == strand2:
        return 0

    # Initialize hamming value as the difference in length between strand1 and
    # strand2.
    hamming_val = abs(len(strand1) - len(strand2))

    index = 0
    for nucleotide in strand1:
        # The case where strand2 is shorter than strand1.
        if index >= len(strand2):
            break

        # Compare nucleotide in strand1 with equivalent from stand2.
        elif nucleotide != strand2[index]:
            hamming_val += 1

        index += 1

    return hamming_val
