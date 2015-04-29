def to_rna(strand):
    '''Return the strand of DNA as a strand of RNA.'''
    complements = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    try:
        result = [complements[c] for c in strand.upper()]
    except KeyError:
        raise ValueError('Incorrectly formatted DNA Strand', strand)
    return ''.join(result)
