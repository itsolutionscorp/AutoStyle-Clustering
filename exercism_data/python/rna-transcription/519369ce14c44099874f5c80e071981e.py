def to_rna(strand):
    rna_compliment = '';
    for nucleotide in strand:
        if nucleotide == 'G':
            rna_compliment += 'C';
        if nucleotide == 'C':
            rna_compliment += 'G';
        if nucleotide == 'T':
            rna_compliment += 'A';
        if nucleotide == 'A':
            rna_compliment += 'U';
    return rna_compliment;
