__author__ = 'linda'

def hamming(strand1, strand2):
    hammingDistance = 0
    pairs = zip(strand1, strand2)
    for nucleotide1, nucleotide2 in pairs:
        if nucleotide1 != nucleotide2:
            hammingDistance += 1
    hammingDistance += abs(len(strand1) - len(strand2))
    return hammingDistance
