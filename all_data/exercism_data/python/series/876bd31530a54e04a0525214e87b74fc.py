# slice( '0123', 3)
# [[0], [1], [2], [3], [01], [12], [23]...


def slices(string, n):
    string_length = len(string)
    difference = string_length - n
    if n > string_length:
        raise ValueError('String is shorter than requested series length')
    answer = []

    offset = 0
    print difference
    while difference > offset:
        for i in xrange(offset+1, n+1+offset):
            answer.append(string[offset:i])
        offset += 1
    return answer
