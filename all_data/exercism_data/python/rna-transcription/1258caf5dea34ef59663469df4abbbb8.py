#
# Transcribes a DNA string to its complementary RNA string
#

def to_rna(dna):
    complements = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    return ''.join([complements[nucleotide] for nucleotide in dna])
