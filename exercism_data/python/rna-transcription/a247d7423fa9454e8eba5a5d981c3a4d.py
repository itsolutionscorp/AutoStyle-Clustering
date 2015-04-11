rna_seq = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}

def to_rna(code):
    return_code = [rna_seq[item] for item in code]
    return ''.join(return_code)
    
    
