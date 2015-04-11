def to_rna(input):
    to_rna_map = {'G': 'C', 
                  'C': 'G', 
                  'T': 'A', 
                  'A': 'U'}
    return ''.join(to_rna_map[char] for char in input)
