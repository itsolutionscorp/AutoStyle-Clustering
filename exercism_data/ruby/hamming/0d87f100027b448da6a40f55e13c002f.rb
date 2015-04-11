 class Hamming

  def self.compute(strand1, strand2)
    compare_strands(strand1, strand2)
  end

  def self.compare_strands(strand1, strand2)
    zipped = strand1.chars.zip(strand2.chars)
    hamming_count(zipped)  
  end

  def self.hamming_count(zipped_strands)
    zipped_strands.count do |pair| 
      pair[0] != pair[1] unless pair[1] == nil
    end
  end

end
