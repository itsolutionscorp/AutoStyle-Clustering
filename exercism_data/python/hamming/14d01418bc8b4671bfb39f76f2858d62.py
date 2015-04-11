def hamming(one, two):
    first = one
    second = two

    if len(one) < len(two):
        first = two
        second = one

    distance = 0

    for i, nucleotide in enumerate(first):
        try: 
            if nucleotide != second[i]:
                distance += 1   
        except IndexError:
            distance += 1
       
    return distance
