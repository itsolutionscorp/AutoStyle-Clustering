def distance(a, b):
    '''Hamming distance between two strings.'''

    # Assume they are identical
    distance = 0

    # Loop through the letters of each string, adding 1 for each pair that is
    # different.
    for x, y in zip(a, b):
        if x != y:
            distance += 1

    return distance

            
