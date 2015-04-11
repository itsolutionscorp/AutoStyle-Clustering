def distance(strand1, strand2):
    return sum([1 for index, value in enumerate(strand1) if value is not
                strand2[index]])
