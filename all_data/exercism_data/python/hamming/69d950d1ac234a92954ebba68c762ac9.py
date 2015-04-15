def distance(A, B):
    count = 0
    for x, y in zip(A, B):
        if x != y:
            count += 1
    return count
