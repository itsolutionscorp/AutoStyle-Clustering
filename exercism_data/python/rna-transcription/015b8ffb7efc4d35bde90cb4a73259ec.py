from string import maketrans

DNA_NUCLEOTIDES = 'GCTA'
RNA_NUCLEOTIDES = 'CGAU'
TRANTAB = maketrans(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)


def to_rna(strand):
    return strand.translate(TRANTAB)
