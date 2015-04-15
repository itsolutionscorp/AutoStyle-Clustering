def distance(one, two):
    dna_one = list(one)
    dna_two = list(two)
    count = 0
    pos = 0

    for letter in dna_one:
        if (letter is not dna_two[pos]):
            count += 1
        pos += 1

    return count
