#By counting the number of differences between two homologous DNA strands
#taken from different genomes with a common ancestor, we get a measure of
#the minimum number of point mutations that could have occurred on the
#evolutionary path between the two strands.


def distance(DNA_strand1,DNA_strand2):
    '''
    Calculates the hamming distance between two homologous DNA strands
    by counting the differences
    :param DNA_strand1: String
    :param DNA_strand2: String
    :return: int
    '''
    hamming_distance = 0

    if len(DNA_strand1) == len(DNA_strand2):
        for idx in range(len(DNA_strand1)):
            if DNA_strand1[idx] != DNA_strand2[idx]:
                hamming_distance += 1

    return hamming_distance
