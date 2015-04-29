from itertools import izip

def distance(strand_1, strand_2):
    return sum(nucl_1 != nucl_2 for nucl_1, nucl_2 in izip(strand_1, strand_2))
