def distance(left, right):
    if len(left) != len(right):
        raise Exception('Both arguments must be of equal length')
    hamming = 0
    index = 0
    while index != len(left):
        if left[index] != right[index]:
            hamming += 1
        index += 1
    return hamming
