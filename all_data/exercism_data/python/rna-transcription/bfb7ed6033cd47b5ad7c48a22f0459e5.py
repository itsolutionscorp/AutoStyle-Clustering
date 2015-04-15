#Translates a string of dna to a string of rna

def to_rna(dna):   
    rna = ""

    dna_to_rna_translator = {
        'G' : 'C',
        'C' : 'G',
        'T' : 'A',
        'A' : 'U'
    }
    
    for nucleotide in dna:
        if (nucleotide not in dna_to_rna_translator):
            return "Invalid dna input! Only G,C,T, and A characters are accepted"
        
        rna += dna_to_rna_translator[nucleotide]

    return rna
