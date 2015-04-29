def hamming(strand1, strand2):
    '''
    Calculates the hamming distance between strand1 and strand2
    '''
    count = 0
    distance = 0
    # Account for differences in strand lengths
    additional = abs(len(strand1) - len(strand2))

    # Loop over each character in strand1
    for x in strand1:
        # Make sure we don't check a index over length of strand2
        if count < len(strand2):
            # Check if the characters at position COUNT in each strand are different
            if x != strand2[count]:
                # If different, add 1 to distance total
                distance += 1
            count+=1

    #add the additional value to distance
    return distance + additional
