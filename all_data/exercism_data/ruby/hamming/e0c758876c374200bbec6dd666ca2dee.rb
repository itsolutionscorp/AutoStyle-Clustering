class DNA
  def initialize(dna)
    @dna = dna
  end
  
  def hamming_distance(dna)
    @dna.chars.zip(dna.chars).count do |(a,b)| 
      a && b && a != b
    end
  end
end
