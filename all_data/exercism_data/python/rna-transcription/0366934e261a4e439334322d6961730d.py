#Translates a string of dna to a string of rna

def to_rna(dna):   
    dna = dna.upper()
    rna = ""

    dna_to_rna_translator = {'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}
    
    for char in dna:
        if (char not in dna_to_rna_translator):
            return "Invalid dna input! Only G,C,T, and A characters are accepted"
        
        rna += dna_to_rna_translator[char]

    return rna
