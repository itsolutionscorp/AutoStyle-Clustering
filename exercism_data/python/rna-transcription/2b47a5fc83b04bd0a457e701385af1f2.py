def to_rna(dna):
    transposition = {'G': 'C',
                     'C': 'G',
                     'T': 'A',
                     'A': 'U'
                     }

    rna = ''.join([transposition[nucleotid] for nucleotid in dna])
    return rna
