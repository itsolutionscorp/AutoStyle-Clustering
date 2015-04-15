def distance(dna1, dna2):

   if len(dna1) != len(dna2):
      print "Hamming distance is only defined for sequences of equal length"
      exit(2)

   distance = 0

   for i in range(0, len(dna1)):
      if dna1[i] != dna2[i]:
         distance = distance + 1

   return distance
