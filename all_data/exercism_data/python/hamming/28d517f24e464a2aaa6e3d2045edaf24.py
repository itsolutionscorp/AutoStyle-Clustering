def distance(dna1,dna2):
    ham=0
    for i in range(len(dna1)):
        if dna1[i]!=dna2[i]: ham+=1
    return ham
