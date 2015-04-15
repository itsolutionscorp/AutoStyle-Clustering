class Hamming
  def self.compute(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).count do |base_a, base_b|
      base_b && base_a != base_b
    end
  end
end
