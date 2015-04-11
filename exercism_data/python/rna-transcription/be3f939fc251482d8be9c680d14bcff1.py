def to_rna(dna):
    rna = dna.replace('C', 'g').replace('G', 'c').replace('T','a').replace('A','u').upper()

    
    
    return rna
