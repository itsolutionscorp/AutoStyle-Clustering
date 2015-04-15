DNA_TO_RNA = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(input):
    output = ''
    for c in input:
        output += DNA_TO_RNA[c]
    return output
