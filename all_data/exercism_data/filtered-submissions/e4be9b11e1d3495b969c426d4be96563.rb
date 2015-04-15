def compute(dna1, dna2)


  x = 0
  hammingdistance = 0


  while x < dna1.length and x < dna2.length
    hammingdistance += 1 unless dna1[x] == dna2[x]
    x += 1
  end

  hammingdistance

end