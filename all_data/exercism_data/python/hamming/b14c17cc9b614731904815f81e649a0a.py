def distance(dna1,dna2):
    try:
        distance=0
        for i in range(0,len(dna1)):
            if dna1[i]!=dna2[i]:
                distance+=1
        return distance
      # return len(set.difference(set(dna1),set(dna2))) Alternative way not working
    except AttributeError:
        print('Not the same length of both dna strangs')
