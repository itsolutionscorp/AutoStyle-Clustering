__author__ = 'Greg'

def hamming(sequence1, sequence2):
    """ counts the number of characters varying between 2 strings.
    """
    difference = 0
    len1 = len(sequence1)
    len2 = len(sequence2)
    difference += abs(len1 - len2)
    
    if(len1 > len2) or (len1 == len2):
        for x in range(len2):
            if sequence1[x] != sequence2[x]:
                difference += 1

    else: 
        for x in range(len1):
            if sequence1[x] != sequence2[x]:
                difference += 1

    return difference
