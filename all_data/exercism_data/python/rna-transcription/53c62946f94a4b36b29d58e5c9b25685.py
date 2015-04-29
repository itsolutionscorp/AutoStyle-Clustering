from string import maketrans

translate_to_rna = maketrans('GCTA','CGAU')

def to_rna(strand):
    return strand.translate(translate_to_rna)
