def rna(strand):
    if strand == 'G':
        return 'C'
    elif strand == 'C':
        return 'G'
    elif strand == 'T':
        return 'A'
    elif strand == 'A':
        return 'U'


def to_rna(dna):
    rna_ans = "".join(rna(i) for i in dna)
    return rna_ans
