DNA_TO_RNA = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}

def to_rna(dna):
    """ Takes a string of DNA and returns the equivalent RNA string.
    :param dna: String of DNA
    :return: String of RNA
    """
    return "".join(DNA_TO_RNA[x.upper()] for x in dna)
