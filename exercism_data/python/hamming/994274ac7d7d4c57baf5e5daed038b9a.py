def distance(str1, str2):
    l1 = list(str1)
    l2 = list(str2)
    ''' 
    Hamming distance is only defined for strings of the
    same length.
    '''
    assert(len(l1) == len(l2))
    dist = 0
    for c1,c2 in zip(l1, l2):
        if c1!=c2:
            '''
            Hamming distance for strings of the same length
            is defined by the number of one-char differences 
            between the strings.
            '''
            dist+=1
    return dist
