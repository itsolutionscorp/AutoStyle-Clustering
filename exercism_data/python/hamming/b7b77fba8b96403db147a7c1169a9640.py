def distance(dna,dna1):
    if len(dna) != len(dna1):
        return ('DNA sequences are not equal')
    x = 0
    count = 0
    while x < len(dna):
        if dna[x] != dna1[x]:
            count = count + 1
            
        x = x + 1
        
    
    return count
    
    

#print(hamming('afdfg', 'asdgf'))
