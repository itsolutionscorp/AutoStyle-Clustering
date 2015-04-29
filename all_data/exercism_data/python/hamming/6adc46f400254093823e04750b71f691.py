def distance(dna1, dna2):
    if dna1 == dna2 or len(dna1) != len(dna2):
        return 0
    else:
        total_diff = 0
        for i in range(len(dna1)):
            if dna1[i] != dna2[i]:
                total_diff += 1
        return total_diff
