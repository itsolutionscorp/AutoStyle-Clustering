from string import maketrans

DNA_TO_RNA = maketrans('GCTA', 'CGAU')

def to_rna(seq):
    return seq.translate(DNA_TO_RNA)
