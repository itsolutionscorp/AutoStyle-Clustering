def to_rna(string):
    dna_to_rna_map = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    charList = []
    for c in string:
        if c in dna_to_rna_map.keys():
            charList.append(dna_to_rna_map[c])
        else:
            charList.append(' ')
    result = ''.join(charList)
    return result
    
