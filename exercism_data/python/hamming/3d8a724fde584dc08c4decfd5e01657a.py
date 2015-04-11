def distance(dna, mutant_dna):
    count = 0
    comparison = zip(dna, mutant_dna)
    for normal, mutant in comparison:
        if normal != mutant:
            count += 1
    return count
