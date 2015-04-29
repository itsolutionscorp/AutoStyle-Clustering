def to_rna(dna):
    rna = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    return ''.join(map(lambda x: rna[x], dna))
# map is generally faster than list comprehension, but I like its readability
# better:
#   return ''.join([rna[x] for x in dna])
