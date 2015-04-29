DNA_TO_RNA_CONVERSION = str.maketrans('GCTA', 'CGAU')

def to_rna(dna):
    return dna.translate(DNA_TO_RNA_CONVERSION)
