def hamming(genomeA, genomeB):
    lengthA, lengthB = len(genomeA), len(genomeB)
    #minimum hamming is number of extra characters
    hamming          = abs( lengthA - lengthB )
    #length up to which to loop, assume A is shorter
    length           = lengthA
    
    #trim the longer genome down to the length of the shorter one
    if lengthA > lengthB:
        genomeA = genomeA[:-hamming]
        #revert upon initial assumption, B is the shorter one
        length  = lengthB
    elif lengthB > lengthA:
        genomeB = genomeB[:-hamming]

    for position in range(0, length):
        if not genomeA[position] == genomeB[position]:
            hamming += 1
            
    return hamming
