def to_rna(dna_strand):
    dna_rna_map = {"G":"C", "C":"G", "T":"A", "A":"U"}
    rna = ""
    for nucleotide in dna_strand:
        rna += dna_rna_map[nucleotide]
    return(rna)
