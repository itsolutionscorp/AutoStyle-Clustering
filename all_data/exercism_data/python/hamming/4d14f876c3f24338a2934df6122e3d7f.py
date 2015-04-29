def hamming(input_1, input_2):

    # Check the simplest case before doing anything more complicated
    if input_1 == input_2:
        return 0

    # Since any differences in length are considered 'misses',
    # Use that as the base distance.
    distance = abs(len(input_1) - len(input_2))

    comparison = zip(input_1, input_2)
    for x, y in comparison:
        if x != y:
            distance += 1
        
    return distance
