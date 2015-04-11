
def distance(dna, rna):
    hamming = 0
    for i in range(len(dna)):
        if dna[i] != rna[i]:
            hamming += 1
    return hamming
