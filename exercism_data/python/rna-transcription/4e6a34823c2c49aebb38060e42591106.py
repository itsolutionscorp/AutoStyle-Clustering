def to_rna(input):
    RNA = ""
    
    for char in input:
        if char == 'G':
            RNA += 'C'
        elif char == 'C':
            RNA += 'G'
        elif char == 'T':
            RNA += 'A'
        elif char == 'A':
            RNA += 'U'
        
    return RNA
