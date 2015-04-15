def distance(str1, str2):
    ''' 
    Hamming distance is only defined for strings of the
    same length.
    '''
    assert(len(str1) == len(str2))
    dist = 0
    for c1,c2 in zip(str1, str2):
        if c1!=c2:
            '''
            Hamming distance for strings of the same length
            is defined by the number of one-char differences 
            between the strings.
            '''
            dist+=1
    return dist
