def distance(dna1, dna2):
    return sum([1 for (n1, n2) in zip(dna1, dna2)
            if n1 != n2])
