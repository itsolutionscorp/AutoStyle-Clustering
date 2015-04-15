def distance(*strands):
    # Input Validation
    assert len(strands) == 2
    assert len(strands[0]) == \
           len(strands[1])
    
    # Match the strands and remove set duplicates
    matches   = set(zip(*strands))
    duplicate = set(zip('GCAT', 'GCAT'))
    return  len(matches - duplicate)
