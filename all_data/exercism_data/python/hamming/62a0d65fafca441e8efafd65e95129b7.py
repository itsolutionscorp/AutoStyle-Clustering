def hamming(input_1, input_2):

    # Check the simplest case before doing anything more complicated
    if input_1 == input_2:
        return 0

    # Since any differences in length are considered 'misses',
    # Use that as the base distance.
    distance = abs(len(input_1) - len(input_2))

    # determine the shorter of the two inputs
    length = min(len(input_1), len(input_2))

    # iterate through both inputs and compare.  If characters don't
    # match, increment the distance.
    i = 0
    while i < length: 
        if input_1[i] != input_2[i]:
            distance += 1
        i += 1
        
    return distance
