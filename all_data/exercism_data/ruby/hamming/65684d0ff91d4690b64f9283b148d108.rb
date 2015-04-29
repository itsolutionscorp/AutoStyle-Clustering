class Hamming 
  def self.compute(dna1,dna2) 
    differences = 0 
      (0..(dna1.size - 1)).each do |n| 
        differences += 1 if ( dna1[n] != dna2[n] ) 
      end 
    return differences
  end
end
