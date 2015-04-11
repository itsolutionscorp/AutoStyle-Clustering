#
# Converts DNA code to RNA based on given conversions
#


def to_rna(dna):
    rna = ''
    convert = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    for nucl in dna:
        rna += convert[nucl]
    return rna
