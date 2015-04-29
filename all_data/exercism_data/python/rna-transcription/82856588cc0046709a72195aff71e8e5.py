def to_rna(dna):
    """changes dna to rna"""

    #used a temp * to avoid mix ups
    dna = dna.replace('C', '*')
    dna = dna.replace('G', 'C')
    dna = dna.replace('*', 'G')

    dna = dna.replace('A', 'U')    
    dna = dna.replace('T', 'A')

    return dna
