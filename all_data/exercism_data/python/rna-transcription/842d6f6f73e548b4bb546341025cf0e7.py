def to_rna(dna):
    rna = ''
    for nucleotide in dna:
        switcher = ''
        if nucleotide == 'G':
            switcher = 'C'
        elif nucleotide == 'C':
            switcher = 'G'
        elif nucleotide == 'T':
            switcher = 'A'
        else:
            switcher = 'U'
            
        rna = rna + switcher
        
    return rna    
