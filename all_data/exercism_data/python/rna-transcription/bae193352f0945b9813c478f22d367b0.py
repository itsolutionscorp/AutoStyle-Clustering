def to_rna(dna_strand):
    rna_strand = ''
    for nucleotide in dna_strand:
        if nucleotide == 'G':
            rna_strand += 'C'
        elif nucleotide == 'C':
            rna_strand += 'G'
        elif nucleotide == 'T':
            rna_strand += 'A'
        elif nucleotide == 'A':
            rna_strand += 'U'
    return rna_strand
