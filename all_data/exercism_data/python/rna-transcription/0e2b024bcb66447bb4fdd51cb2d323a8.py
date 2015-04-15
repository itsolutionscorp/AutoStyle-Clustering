DNA_TO_RNA = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(input):
    return ''.join(DNA_TO_RNA[x] for x in input)
