def to_rna(dna_strand):

    rna_strand = ""
    
    for strand in dna_strand :
        if strand == "G":
            rna_strand += "C"
        if strand == "C":
            rna_strand += "G"
        if strand == "T":
            rna_strand += "A"
        if strand == "A":
            rna_strand += "U"
            
    return rna_strand
        
        
