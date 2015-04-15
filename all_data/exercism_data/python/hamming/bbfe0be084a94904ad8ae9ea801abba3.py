def hamming(dna1, dna2):

    if dna1 == dna2:
        return 0

    hamming = 0
    (dna1, dna2) = sorted([dna1, dna2], cmp=comp_len)
    for pos, nucl in enumerate(dna1):
        hamming += 0 if dna2[pos] == nucl else 1

    if len(dna1) != len(dna2):
        hamming += abs(len(dna1) - len(dna2))

    return hamming

def comp_len(a, b):
    return len(a) - len(b)
