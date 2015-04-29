def compute(dna1, dna2)
  
  #Set inital value for control and datacollection variables
  x = 0
  hammingdistance = 0
  
  #Loop through and calculate hamming distance
  while x < dna1.length and x < dna2.length
    hammingdistance += 1 unless dna1[x] == dna2[x]
    x += 1
  end
  #return hamming distance
  hammingdistance
    
end