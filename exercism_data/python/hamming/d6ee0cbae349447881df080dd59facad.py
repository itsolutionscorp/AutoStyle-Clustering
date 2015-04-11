def hamming(x, y):
    "Find and return Hamming-difference in two DNA strands"
    count = 0
    for i in range(max(len(x), len(y))):
        try:
            if x[i] != y[i]:
                count += 1
        except IndexError, e:
            count += max(len(x), len(y)) - min(len(x),len(y))
            return count
    return count
