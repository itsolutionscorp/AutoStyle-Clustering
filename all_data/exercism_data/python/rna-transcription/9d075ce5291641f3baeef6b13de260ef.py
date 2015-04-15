def to_rna(input):
    RNA = ""
    
    for char in input:
        if char == 'G':
            RNA += 'C'
        if char == 'C':
            RNA += 'G'
        if char == 'T':
            RNA += 'A'
        if char == 'A':
            RNA += 'U'
        
    return RNA
