def to_rna(strand):
    RNA_COMPLEMENT_TO_DNA = {'A': 'U', 'C': 'G', 'G': 'C', 'T': 'A' }
    get_complement = lambda(base): RNA_COMPLEMENT_TO_DNA[base]
    
    complements = []
    for base in list(strand):
        complements.append(get_complement(base))
    
    return ''.join(complements)
