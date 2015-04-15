def to_rna(dna_in):

    dna = "ACGT"
    rna = "UGCA"

    dna_to_rna = str.maketrans(dna,rna)
    return (dna_in.translate(dna_to_rna))
