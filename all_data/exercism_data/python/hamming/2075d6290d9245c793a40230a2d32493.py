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

    if len(DNA_strand1) != len(DNA_strand2):
        raise ValueError("hamming distance requires equal length DNA strands")# not defined
    else:
        return sum(1 for (x,y) in zip(DNA_strand1,DNA_strand2) if x != y)
