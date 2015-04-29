def distance(seq_a, seq_b):
    if len(seq_a) != len(seq_b):
        raise ValueError

    diff = 0
    for i in range(len(seq_a)):
        if seq_a[i] != seq_b[i]:
            diff += 1
            
    return diff
    
