def compute(dna1, dna2)
    return -1 if dna1.length != dna2.length
    
    hamming = 0
    (0..(dna1.length - 1)).each do |index|
      hamming += 1 if dna1[index] != dna2[index]
    end
    
    hamming
  end