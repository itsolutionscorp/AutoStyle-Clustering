def hamming(sequence1,sequence2):

    length1=len(sequence1)
    length2=len(sequence2)

    distance=abs(length1-length2)
    minlength=min(length1,length2)

    
    for tide in range(minlength):
        distance += int(sequence1[tide]!=sequence2[tide])
    return distance
