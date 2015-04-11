def to_rna(dna_strand):
    rna_strand = ""
    for nuc in dna_strand:
        if nuc == "G":
            rna_strand += "C"
        elif nuc == "C":
            rna_strand += "G"
        elif nuc == "T":
            rna_strand += "A"
        elif nuc == "A":
            rna_strand += "U"
    return rna_strand
