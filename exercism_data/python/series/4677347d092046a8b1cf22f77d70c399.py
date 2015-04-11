def slices(seq, length):
    
    if len(seq) < length or not length:
        raise ValueError  
    return [[int(i) for i in seq[i:i+length]] 
                for i in xrange(len(seq)-length+1)]
