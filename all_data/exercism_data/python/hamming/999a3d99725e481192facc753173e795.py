
def hamming(original, mutation):
    count = 0
    for index in list(zip(original, mutation)):
        if index[0] is not index[1]:
            count += 1
    count += abs(len(original) - len(mutation))
    return count
