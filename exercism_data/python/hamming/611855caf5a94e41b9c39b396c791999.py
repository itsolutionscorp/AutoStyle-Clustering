def distance(strand1, strand2):

    hamming = 0
    
    # Test case no longer says fill the short string
    if len(strand1) != len(strand2):
        raise Exception("Different length strands")

    for a, b in zip(strand1, strand2):
        if a != b:
            hamming += 1
            
    return hamming

    
