def distance(strands1, strands2):

    dist = 0

    for index, char in enumerate(strands1):
        if strands2[index] != char:
            dist += 1
    return dist
