def distance(dna1, dna2):
    if len(dna1) != len(dna2):
        return 0
    result = 0
    for (char1, char2) in zip(dna1, dna2):
        if char1 != char2:
            result += 1

    return result
