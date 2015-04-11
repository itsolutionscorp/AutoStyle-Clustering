def hamming(dna1, dna2):
    count = 0
    i = 0

    if len(dna1) > len(dna2):
        long = dna1
        short = dna2
    else:
        long = dna2
        short = dna1

    for letter in short:
        if letter != long[i]:
            count += 1
        i += 1

    count += len(long) - len(short)

    return count
