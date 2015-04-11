rna_seq = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}

def to_rna(code):
    new_seq = []
    for items in code:
        for item in items:
            if item in rna_seq:
                new_seq.append(rna_seq[item])
    return ''.join(new_seq)
    
    
