def distance(a, b):
    hamming = 0
    length = len(a)
    
    if len(b) != length:
        return 'DNA strands not of equal lengths.'

    for i in range(0,length):
        if a[i] != b[i]:
            hamming +=1
    
    return hamming
