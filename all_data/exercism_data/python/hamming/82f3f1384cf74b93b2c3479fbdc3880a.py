from itertools import izip

def distance(strand_a, strand_b):
    """Return the Hamming distance between two equal-length DNA strands.
    
    Note: The Hamming distance is the number of nucleotide substitutions
          separating the two strings. It does not measure insertions or
          deletions and is undefined for strands of differing lengths.
    
    Parameters:
        strand_a, strand_b -- the DNA strands to compare
    """
    if len(strand_a) != len(strand_b):
        raise ValueError('Hamming distance undefined'
                         'for strings of unequal length.')
    return sum(1 for a,b in izip(strand_a,strand_b) if a != b)
    
if __name__ == '__main__':
    strand_a = raw_input("First strand: ")
    strand_b = raw_input("Second strand: ")
    print distance(strand_a, strand_b)
