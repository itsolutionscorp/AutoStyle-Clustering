def distance(original, copy):
    '''counts number of errors in DNA replication'''
    
    return sum([1 for i in range(len(original)) if original[i] != copy[i]])
