def to_rna(dna_in):
    
    dna_to_rna={'A':'U','C':'G','G':'C','T':'A'}

    return ''.join([dna_to_rna.get(c, '') for c in list(dna_in)])
