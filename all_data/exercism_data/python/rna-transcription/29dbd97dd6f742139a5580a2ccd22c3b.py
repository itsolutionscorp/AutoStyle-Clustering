complement_map = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(nucleotides):
    complement = ""
    for nucleotide in nucleotides:
        complement += complement_map[nucleotide]
    return complement
