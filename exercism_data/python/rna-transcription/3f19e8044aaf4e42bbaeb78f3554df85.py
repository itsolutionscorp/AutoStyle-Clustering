
def to_rna(s): 
    basecomplement = {'T': 'A', 'C': 'G', 'G': 'C', 'A': 'U'} 
    letters = list(s) 
    letters = [basecomplement[base] for base in letters] 
    return ''.join(letters)
