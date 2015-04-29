def to_rna(dna_seq):
    '''
    tanslates the nucleotids of dna sequence into
    their equivalents in rna
    '''

    trans_dict = {'G': 'C', 'C':'G', 'T':'A', 'A': 'U'}
    rna_seq = ''

    for nuc in dna_seq:
        rna_seq += trans_dict.get(nuc)

    return rna_seq
