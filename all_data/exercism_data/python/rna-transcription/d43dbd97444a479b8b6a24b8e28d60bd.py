def to_rna(dna_chain):
    rna_lookup={'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    
    rna_chain=""
    for dna_base in dna_chain:
        if( dna_base in rna_lookup ):
            rna_chain+=rna_lookup[dna_base]
    
    return rna_chain
        
