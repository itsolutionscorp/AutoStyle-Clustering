def hamming(first_string, second_string):
    shortest = min( len(first_string), len(second_string) )
    longest = max( len(first_string), len(second_string) )
    distance = longest - shortest
    
    for i in range(shortest):
        if first_string[i] != second_string[i]:
            distance += 1
            
    return distance

    
    
