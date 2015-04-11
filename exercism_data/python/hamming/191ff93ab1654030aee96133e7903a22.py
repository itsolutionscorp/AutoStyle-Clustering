def hamming(strandOne, strandTwo):

    count = 0
    i = 0

    # Strand lengths are NOT equal
    if not len(strandOne) == len(strandTwo):
        
        # All excess letters count as a mutation
        count += abs(len(strandOne) - len(strandTwo))

        # Iterate through smallest strand
        if len(strandOne) < len(strandTwo):
            for char in strandOne:
                if not char == strandTwo[i]:
                   count += 1
                i += 1
                
        if len(strandOne) > len(strandTwo):
            for char in strandTwo:
                if not char == strandOne[i]:
                    count += 1
                i += 1


    # Strand lengths are equal
    if len(strandOne) == len(strandTwo):       
        for char in strandOne:
            if not char == strandTwo[i]:
                count += 1               
            i += 1
            
    return count
