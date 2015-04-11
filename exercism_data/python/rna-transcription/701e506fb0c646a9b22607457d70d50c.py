def to_rna(dna_strand):
    rna_equiv = {'C': 'G', 'G': 'C', 'T': 'A', 'A': 'U',}
    rna_strand = ""
    for nucleotide in dna_strand:
        rna_strand += rna_equiv[nucleotide]
    return rna_strand
