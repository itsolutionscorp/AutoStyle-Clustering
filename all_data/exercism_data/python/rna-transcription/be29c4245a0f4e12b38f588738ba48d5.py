DNA = 'CATG'
RNA = 'GUAC'
def to_rna(string):
    return string.translate(string.maketrans(DNA, RNA))
