rna_complement = {
    'G':'C',
    'C':'G',
    'T':'A',
    'A':'U',
}

def to_rna(dna_strand):
    return ''.join(rna_complement[nucleotide] for nucleotide in dna_strand)
