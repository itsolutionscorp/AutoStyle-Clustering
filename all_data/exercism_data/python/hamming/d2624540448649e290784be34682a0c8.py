def hamming(dna, rna):
    count = 0
    i = 0
    
    if len(dna) > len(rna):
        long = dna
        short = rna
    else:
        long = rna
        short = dna

    for letter in short:
        if letter != long[i]:
            count += 1
        i += 1

    count += len(long) - len(short)

    return count
