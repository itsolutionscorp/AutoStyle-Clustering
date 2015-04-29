def distance(dna1, dna2):
    if len(dna1) == len(dna2):
        diff = 0
        for i in range(len(dna1)):
            if dna1[i] != dna2[i]:
                diff += 1
        return diff
    else:
        print "The length of the strands do not match."
        return False
      

#distance('GGACGGATTCTG', 'AGGACGGATTCT')
