#Written for Exercism (4)

def to_rna(dna):
    #empty string to hold rna sequence
    rna = ""
    
    #iterate through dna and switching appropriate chars
    for char in dna:
        if char == 'G':
            rna += 'C'
        elif char == 'C':
            rna += 'G'
        elif char == 'A':
            rna += 'U'
        else:
            rna += 'A'
    
    return rna
