def to_rna(strand):
    RNA_COMPLEMENT_TO_DNA = {'A': 'U', 'C': 'G', 'G': 'C', 'T': 'A' }

    complements = map(lambda(base): RNA_COMPLEMENT_TO_DNA[base], list(strand))
    
    return ''.join(complements)
