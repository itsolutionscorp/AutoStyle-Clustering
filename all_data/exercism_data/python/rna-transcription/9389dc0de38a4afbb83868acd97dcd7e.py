def to_rna(dna_strand):
    rna_strand = ""
    for nuc in dna_strand:
        if nuc == "G":
            rna_strand = rna_strand + "C"
        elif nuc == "C":
            rna_strand = rna_strand + "G"
        elif nuc == "T":
            rna_strand = rna_strand + "A"
        else:
            rna_strand = rna_strand + "U"
    return rna_strand
