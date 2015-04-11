dna_to_rna = {'G':'C','C':'G','T':'A','A':'U'}
def to_rna(dna):
    response = ''
    for character in dna:
        response += dna_to_rna.get(character,'')
    return response
