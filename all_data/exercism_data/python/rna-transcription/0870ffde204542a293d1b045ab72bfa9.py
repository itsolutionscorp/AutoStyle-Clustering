def to_rna(dna_string):
    ''' Translates the DNA input to RNA output '''

    translate_dna_to_rna = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'}

    return ''.join([translate_dna_to_rna[x] for x in dna_string])
