def to_rna(strand):

    dna_to_rna = {'G' : 'C',
                  'C' : 'G',
                  'T' : 'A',
                  'A' : 'U'}

    return ''.join([dna_to_rna[mol] for mol in strand])
