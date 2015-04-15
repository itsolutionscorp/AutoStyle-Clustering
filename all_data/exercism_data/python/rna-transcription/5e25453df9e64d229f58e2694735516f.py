dna_to_rna = {
    'G' : 'C',
    'C' : 'G',
    'T' : 'A',
    'A' : 'U'
}    

def to_rna(dna_strand):
    rna = ''
    for d in dna_strand:
        rna += dna_to_rna[d]
    return rna        
    
