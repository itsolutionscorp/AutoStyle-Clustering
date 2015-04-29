# made by ez.
# to_rna

def to_rna(dna):
    def change(t):
        if t == 'C':
            return 'G'
        elif t == 'G':
            return 'C'
        elif t == 'T':
            return 'A'
        elif t == 'A':
            return 'U'
        else:
            return ''
        
    return ''.join(map(change, dna))
