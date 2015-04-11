# fourth exercism
# Function to write dna to rna

    
#Solution with dictionary
def to_rna(dna_strand):
    dict = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return ''.join([dict[dna] for dna in dna_strand])
