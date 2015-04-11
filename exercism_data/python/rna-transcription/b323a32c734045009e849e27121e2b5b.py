def to_rna(string):
    result=""
    for i, c in enumerate(string):
        if(c=='G'):
            result += 'C'
        elif(c=='C'):
            result += 'G'
        elif(c=='T'):
            result += 'A'
        elif(c=='A'):
            result += 'U'
        else:
            print 'Panic!'
    return result
        
