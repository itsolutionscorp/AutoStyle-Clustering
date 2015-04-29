COMPLEMENTS = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna):
    result = [] 
    for nucleotid in dna:
        result.append(COMPLEMENTS[nucleotid])

    return ('').join(result)
