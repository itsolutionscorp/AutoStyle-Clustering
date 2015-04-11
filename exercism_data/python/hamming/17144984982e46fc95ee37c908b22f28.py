def distance(seq1, seq2):
    hammings = 0 
    for i, j in zip(seq1, seq2): #loops over two lists in parallel
       if(i != j):
            hammings = hammings + 1   
    return hammings
