class UnequalLength(Exception):
    def __str__(self):
        return 'Hamming length is not defined for unequal strands!'

def distance(strand1, strand2):
    hamming = 0

    if len(strand1) != len(strand2):
        raise UnequalLength

    for i in range(len(strand1)):
        if strand1[i] != strand2[i]:
            hamming += 1

    return hamming
