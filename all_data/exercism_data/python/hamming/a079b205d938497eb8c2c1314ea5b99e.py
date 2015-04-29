def hamming (first, second):
        
    errors = sum( i != j for i,j in zip(first,second))

    return errors
