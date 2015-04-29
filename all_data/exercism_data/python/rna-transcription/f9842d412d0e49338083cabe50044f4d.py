import string


def to_rna(aString):
    aString = aString.strip()
    trans_table = string.maketrans('GCTA', 'CGAU')
    return string.translate(aString, trans_table)
