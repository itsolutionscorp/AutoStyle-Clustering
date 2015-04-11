def distance(dna1,dna2):
    
    count = 0
    for i,j in zip(dna1,dna2):
       if ord(i)^ord(j):
           count += 1

    return count
