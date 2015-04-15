def distance(strand1, strand2):
    hdistance = 0
    if len(strand1) != len(strand2):
        return -1

    for i in range(len(strand1)):
        if strand1[i].upper() in ['C','G','A','T'] and strand2[i].upper() in ['C','G','A','T']:
            hdistance += not(strand1[i] == strand2[i])
        else:
            return -1

    return hdistance
