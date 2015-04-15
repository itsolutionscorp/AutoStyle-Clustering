def to_rna(dna):
    dna_list = list(dna)
    x = 0
    while x < len(dna_list):
        # read the letters and assign a variable;
        if dna_list[x] == 'A':
            rna = 1        
        if dna_list[x] == 'G':
            rna = 2            
        if dna_list[x] == 'T':
            rna = 3            
        if dna_list[x] == 'C':
            rna = 4
            
        # read variable and change letter; prevents letters from being
        # read and changed multiple times        
        if rna == 1:
            dna_list[x] = 'U'
        if rna == 2:
            dna_list[x] = 'C'
        if rna == 3:
            dna_list[x] = 'A'
        if rna == 4:
            dna_list[x] = 'G'
            
        x = x+1
    dna = "".join(dna_list)
    return dna
