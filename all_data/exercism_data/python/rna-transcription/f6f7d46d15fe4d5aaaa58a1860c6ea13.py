from string import maketrans
def to_rna(dna):
    inputs = "GCTA"
    outputs = "CGAU"
    map = maketrans(inputs,outputs)
    return dna.translate(map)
