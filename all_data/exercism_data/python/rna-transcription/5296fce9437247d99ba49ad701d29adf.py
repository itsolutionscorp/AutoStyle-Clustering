import string
table = string.maketrans('GCTA','CGAU')

def to_rna(dna):
    """
    Input: string representing strand of dna
    Output: string representing rna complement
    """ 
    return dna.translate(table)
