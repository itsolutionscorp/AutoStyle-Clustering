def hamming(dna1, dna2):
    counter = 0

    long_dna = dna1 if len(dna1) > len(dna2) else dna2
    short_dna = dna2 if len(dna1) > len(dna2) else dna1

    for i, dna in enumerate(long_dna):
        try:
            if dna != short_dna[i]:
                counter += 1
        except IndexError:
            counter += 1

    return counter
