__author__ = 'Arno'

def distance(dna1, dna2):
    if not len(dna1) == len(dna2):
        return 'Error: DNA strings of varying lengths'
    else:
        hamming_dist = 0
        dna1 = dna1.upper()
        dna2 = dna2.upper()
        for i in range(len(dna1)):
            if not dna1[i] == dna2[i]:
                hamming_dist += 1
        return hamming_dist
