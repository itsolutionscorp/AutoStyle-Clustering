def to_rna(dna):
    from string import maketrans
    
    dna_nucleotides = "GCTA"
    rna_nucleotides = "CGAU"
    rosetta = maketrans(dna_nucleotides, rna_nucleotides)

    return dna.translate(rosetta)
