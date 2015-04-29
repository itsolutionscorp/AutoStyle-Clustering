def to_rna(dna_strand):
    dna_strand = dna_strand.upper()
    rna_dic = {
        'G':'C',
        'C':'G',
        'T':'A',
        'A':'U'
    }

    rna_strand = ''
    for nucleotide in dna_strand:
        rna_strand += rna_dic[nucleotide]

    return rna_strand
