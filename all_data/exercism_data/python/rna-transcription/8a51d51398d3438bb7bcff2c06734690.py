def to_rna(nucleotide):
    transcribed = list(map(replace, nucleotide))
    result = ""
    for elem in transcribed:
        result += " ".join(map(str, elem))
    return result
   
    
def replace(char):
    if char is 'G':
        return 'C'
    elif char is 'C':
        return 'G'
    elif char is 'A':
        return 'U'
    elif char is 'T':
        return 'A'
