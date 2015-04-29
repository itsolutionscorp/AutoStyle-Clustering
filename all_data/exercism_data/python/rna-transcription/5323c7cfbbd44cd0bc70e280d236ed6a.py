from string import maketrans

from_dna = maketrans('GATC', 'CUAG')

def to_rna(what):
    return what.translate(from_dna)
