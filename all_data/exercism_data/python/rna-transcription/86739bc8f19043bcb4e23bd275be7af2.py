def complement(nucleotide):
    complements = {
        'G': 'C', 
        'C': 'G', 
        'T': 'A', 
        'A': 'U'
    }
    return complements[nucleotide]

def to_rna(strand):    
    return ''.join([complement(x) for x in strand])
    
