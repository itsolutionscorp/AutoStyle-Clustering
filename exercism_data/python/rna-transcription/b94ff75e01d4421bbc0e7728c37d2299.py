"""
DNA methods
"""

def to_rna(dna):
    """
    Convert DNA to RNA
    """

    rna = {'G': 'C',
           'C': 'G',
           'T': 'A',
           'A': 'U'
          }

    rna_result = ''

    for nucleo in dna:
        rna_result += rna[nucleo]

    return rna_result
# to_rna()
