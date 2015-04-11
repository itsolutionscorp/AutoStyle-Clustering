def to_rna(dna_strand):
    '''
    Returns the RNA transcription
    of the given DNA strand
    '''
    # DNA to RNA complements mapping
    comps = {'G': 'C', 'C': 'G',
             'T': 'A', 'A': 'U'}
    # find rna complement for each nucleotide in dna_strand
    return ''.join([comps[nuc] for nuc in dna_strand])
