'''
Hamming distance
'''

DNA = 'ACTG'

def hamming(dna1, dna2):
    '''
    Calculates the hamming distance between two dna strands
    '''
    if not valid_dna(dna1):
        raise ValueError("%s in not a valid DNA sequence" % dna1)

    if not valid_dna(dna2):
        raise ValueError("%s in not a valid DNA sequence" % dna2)

    # Function to check if the inputs are different
    differ = lambda c1, c2: c1 != c2

    # map pads shorter sequence with None
    return sum(map(differ, dna1, dna2))


def valid_dna(dna):
    '''
    Check the validity of a DNA sequence
    '''
    invalidcodes = dna.translate(None, DNA)

    return not invalidcodes
