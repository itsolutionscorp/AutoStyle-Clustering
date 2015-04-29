def to_rna(dna):
    inputs = "GCTA"
    outputs = "CGAU"
    return dna.translate( str.maketrans( inputs, outputs ) )
