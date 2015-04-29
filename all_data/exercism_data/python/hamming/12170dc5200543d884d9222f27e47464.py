def distance(strand1, strand2):
    if len(strand1) is not len(strand2):
        raise Exception, 'Both strands must be of equal length'
    return sum([strand1[index] == strand2[index] for index in xrange(len(strand1))])
