def distance(original, mutation):
    hamming_distance = 0
    original_bases = list(original)
    mutation_bases = list(mutation)
    for index, originalBase in enumerate(original_bases):
        if originalBase != mutation_bases[index]:
            hamming_distance += 1
    return hamming_distance
