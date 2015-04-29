def to_rna(dna_strand):
    rna_strand = ""
    for letter in dna_strand:
        if letter == "G":
            rna_strand += "C"
        elif letter == "C":
            rna_strand += "G"
        elif letter == "T":
            rna_strand += "A"
        elif letter == "A":
            rna_strand += "U"
    return rna_strand
