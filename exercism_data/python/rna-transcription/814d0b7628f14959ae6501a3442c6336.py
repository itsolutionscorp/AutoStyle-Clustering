def to_rna(dna_strand):
    compl_base = {'A': 'U', 'T': 'A', 'G': 'C', 'C': 'G'}
    rna_strand = ""
    for base in dna_strand:
        rna_strand += compl_base[base]
    return rna_strand
