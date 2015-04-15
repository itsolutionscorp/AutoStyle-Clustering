def distance(seq1, seq2):
    if len(seq1) != len(seq2):
        raise ValueError("The sequences must be the same length.")
        
    count = 0 
    for nuc1, nuc2 in zip(seq1, seq2):
        if nuc1 != nuc2:
            count += 1
    return count
