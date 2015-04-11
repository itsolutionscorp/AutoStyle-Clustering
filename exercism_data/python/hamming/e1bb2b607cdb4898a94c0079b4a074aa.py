def distance(dna1, dna2):
    if len(dna1) == len(dna2):
        diff = 0
        for x, y in zip(dna1, dna2):
        	if x != y:
           		diff += 1
        return diff
    else:
        print "The length of the strings do not match."
        return False
      

#distance('GGACGGATTCTG', 'AGGACGGATTCT')
