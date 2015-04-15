def distance(strand1, strand2):
    
    mismatch = 0
    i = 0
    
    while i < len(strand1):
    
        if strand1[i] != strand2[i]:
            mismatch += 1
            i += 1
        
        else:
            i += 1
    
        return mismatch
