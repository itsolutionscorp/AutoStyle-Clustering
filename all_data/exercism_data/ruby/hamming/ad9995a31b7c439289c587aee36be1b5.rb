class Hamming
  def self.compute strand1, strand2
    differences = 0
    combine_strands(strand1, strand2).each do |dna|
      differences += 1 if dna_differs? dna
    end
    differences
  end
  
  def self.combine_strands strand1, strand2
     strand1.chars.zip(strand2.chars)
  end
  
  def self.dna_differs? dna
    return false if dna[1] == nil
    dna[0] != dna[1]
  end
end
