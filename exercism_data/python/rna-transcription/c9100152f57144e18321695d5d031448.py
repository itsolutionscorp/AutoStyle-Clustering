def to_rna(dna_strand):
    rna_strand = dna_strand.replace('G', '*') #Use a placeholder until the C's are replaced
    rna_strand = rna_strand.replace('C', 'G')
    rna_strand = rna_strand.replace('*', 'C') #Now, we can change the * to C
    rna_strand = rna_strand.replace('A', 'U')
    rna_strand = rna_strand.replace('T', 'A')

    return rna_strand
