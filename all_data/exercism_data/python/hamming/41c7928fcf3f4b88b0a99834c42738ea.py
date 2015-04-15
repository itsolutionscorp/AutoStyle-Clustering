def distance(seqA, seqB):
    dist = 0
    for index, char in enumerate(seqA):
        if index >= len(seqB):
            return dist  # what is correct behaviour in this case?
        if char != seqB[index]:
            dist += 1

        # if B is longer than A, will only return diffs in the first len(A) letters in B
    return dist
