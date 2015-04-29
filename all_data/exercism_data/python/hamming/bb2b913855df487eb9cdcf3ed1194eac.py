def hamming(person1, person2):
    hamming_distance = 0

    if len(person1) > len(person2):
        hamming_distance += len(person1) - len(person2)
        person1 = person1[:len(person2) - 1]

    if len(person2) > len(person1):
        hamming_distance += len(person2) - len(person1)
        person1 = person1[:len(person1) - 1]

    hamming_distance += len([i for i, j in zip(person1, person2) if i != j])

    return hamming_distance
