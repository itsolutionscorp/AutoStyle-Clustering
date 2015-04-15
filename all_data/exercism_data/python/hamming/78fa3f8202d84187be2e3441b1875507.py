def hamming(dna, dnb):

    dna_length = max(len(dna),len(dnb))
    dna += 'X' * (dna_length - len(dna))
    dnb += 'X' * (dna_length - len(dnb))

    count = 0
    for i in range(len(dna)):
        if dna[i] != dnb[i]:
            count +=1

    return count
