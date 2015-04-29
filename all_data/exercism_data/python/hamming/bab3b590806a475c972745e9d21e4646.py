def hamming(seqa, seqb):
    # Make both strings the same length by padding
    seqa = seqa.ljust(len(seqb))
    seqb = seqb.ljust(len(seqa))

    return sum(1 for a, b in zip(seqa, seqb) if a != b)
