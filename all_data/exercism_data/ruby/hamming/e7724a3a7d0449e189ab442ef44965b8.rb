class Hamming
  def self.compute(strand_a, strand_b)
    zipped_strands(strand_a, strand_b).count do |x,y|
      x != y
    end
  end

  def self.zipped_strands(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).select{|x,y|
      x && y
    }
  end
end
