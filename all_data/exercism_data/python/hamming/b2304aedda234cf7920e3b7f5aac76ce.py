def hamming(strand1, strand2):
 
    # initialize the count at zero
    result = 0

    # Go through the smallest number of characters between strand1 and strand2
    for letter in range(0, (len(strand1) + len(strand2) - abs(len(strand1) - len(strand2))) / 2):
        
        # if they differ add 1 to the result
        if strand1[letter] != strand2[letter]:
            result += 1

    # Add the difference between the size of the two strands to the result
    result += abs(len(strand2) - len(strand1))

    return result
