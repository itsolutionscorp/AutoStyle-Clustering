def distance(dna, dna_m):
    if len(dna) != len(dna_m):
        return 'Strands of the same length.'
    res = 0
    i = 0
    while i < len(dna):
        if dna[i] != dna_m[i]:
            res += 1
        i += 1
    return res
