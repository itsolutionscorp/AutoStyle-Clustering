def hamming(dna1, dna2):
    diff = abs(len(dna1)-len(dna2))
    for d1, d2 in zip(dna1, dna2):
        if d1 != d2:
            diff += 1
    return diff

    
