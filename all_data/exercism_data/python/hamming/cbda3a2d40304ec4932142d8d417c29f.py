import operator

def hamming(source, comp):
    #apply the "==" function to all corresponding items in both strings.
    #count False occurrences 
    distance = map(operator.eq, source, comp).count(False)
    return distance
