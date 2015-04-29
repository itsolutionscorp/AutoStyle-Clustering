
def to_rna(strand):
    nucleotides = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    result = ''
    for nucleotide in strand:
        result += nucleotides[nucleotide]
    return result
