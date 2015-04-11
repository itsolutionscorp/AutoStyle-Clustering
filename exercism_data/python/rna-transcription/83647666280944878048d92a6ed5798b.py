def to_rna(sequence):
    rna = sequence
    rna = rna.replace('A', 'U')
    rna = rna.replace('T', 'A')
    rna = rna.replace('C', 'G')
    
    split = rna.split('G')
    split_index = 0
    rna = ''
    
    for index in range(len(sequence)):
        # Insert C instead of G
        if sequence[index] == 'G':
            rna += split[split_index]
            rna += 'C'
            split_index += 1
            
        # Insert G instead of C
        if sequence[index] == 'C':
            rna += split[split_index]
            rna += 'G'
            split_index += 1
    
    return rna + split[split_index]
