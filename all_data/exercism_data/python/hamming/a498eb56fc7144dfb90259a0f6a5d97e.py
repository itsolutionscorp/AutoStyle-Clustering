def distance(dna1, dna2):



    count = 0
    for i, char in enumerate(dna1):
        
        if dna2[i] is not char:
            count += 1

    return count
