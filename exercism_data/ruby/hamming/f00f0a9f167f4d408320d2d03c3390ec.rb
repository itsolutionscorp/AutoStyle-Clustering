class Hamming
  def self.compute(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).count do |x,y|
      next unless y
      x != y
    end
  end
end
