def to_rna(dna_strand):
    dna_strand = dna_strand.upper()
    rna_dic = {
        'G':'C',
        'C':'G',
        'T':'A',
        'A':'U'
    }
    return ''.join([rna_dic[nucleotide] for nucleotide in dna_strand])
