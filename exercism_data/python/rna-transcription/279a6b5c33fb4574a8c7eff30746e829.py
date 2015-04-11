from string import maketrans
"""
DNA methods
"""

def to_rna(dna):
    """
    Convert DNA to RNA
    """

    return dna.translate(maketrans('GCTA', 'CGAU'))
# to_rna()
