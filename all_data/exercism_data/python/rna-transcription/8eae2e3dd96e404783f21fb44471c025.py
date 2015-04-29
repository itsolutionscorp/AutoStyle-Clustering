import string


DNA_TO_RNA_MAP = string.maketrans('GCTA', 'CGAU')


def to_rna(dna_strand):
    return dna_strand.translate(DNA_TO_RNA_MAP)
