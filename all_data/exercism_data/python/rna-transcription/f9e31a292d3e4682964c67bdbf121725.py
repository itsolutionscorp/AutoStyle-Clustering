def to_rna(dna):
    """ Converts a string of DNA nucleotides to its RNA complement. """
    dna_to_rna = dict(zip('GCTA', 'CGAU'))

    return ''.join([dna_to_rna[n] for n in dna])
