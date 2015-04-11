TRANSLATION = {'G':'C',
               'C':'G',
               'T':'A',
               'A':'U'}

def to_rna(dna):
    rna = ''
    
    for letter in dna:
        rna += TRANSLATION[letter]
        
    return rna
