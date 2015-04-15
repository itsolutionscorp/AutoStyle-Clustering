def distance(dna1, dna2):
    if len(dna1) != len(dna2):
        return 0
    result = 0
    for index, char in enumerate(dna1):
        if char != dna2[index]:
            result += 1

    return result
