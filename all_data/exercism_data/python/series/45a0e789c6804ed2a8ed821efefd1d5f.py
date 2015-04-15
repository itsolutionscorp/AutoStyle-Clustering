def slices(sequence, length):
    if length == 0 or length > len(sequence):
        raise ValueError('%s not a valid sublength' % length)

    substrings = [sequence[i:i+length] for i in range(len(sequence)-length+1)]
    return [[int(c) for c in substring] for substring in substrings]

if __name__ == '__main__':
    print slices('01234', 2)
    print slices('97867564', 2)
