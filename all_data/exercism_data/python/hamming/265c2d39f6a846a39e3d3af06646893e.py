def hamming(one, two):
    
    length = max(len(one), len(two))
    distance = 0

    for i in range(length):
        try: 
            if one[i] != two[i]:
                distance += 1   
        except IndexError:
            distance += 1
       
    return distance
