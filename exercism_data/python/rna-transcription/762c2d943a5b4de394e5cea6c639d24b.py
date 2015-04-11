from string import maketrans

translation = maketrans('GCTA','CGAU')

def to_rna(dnaSeq):
    return dnaSeq.translate(translation)
