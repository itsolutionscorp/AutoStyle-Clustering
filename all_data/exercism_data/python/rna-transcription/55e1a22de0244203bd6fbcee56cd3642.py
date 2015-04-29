def to_rna(nucleotides):
    return ''.join(map(complement, nucleotides))

def complement(nucleotide):
    return mappings[nucleotide]

mappings = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
