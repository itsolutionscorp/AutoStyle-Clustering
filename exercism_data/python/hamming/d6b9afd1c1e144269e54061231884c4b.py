def hamming (dna1, dna2):
    hamming_difference = abs (len (dna1) - len (dna2))
    for a in range (len (dna1) if len (dna1) < len (dna2) else len (dna2)):
        if dna1 [a] != dna2 [a]:
            hamming_difference += 1
    return hamming_difference
