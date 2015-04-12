class Hamming
  def compute(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).count do |base, pair|
      base != pair
    end
  end
end
