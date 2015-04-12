class Hamming
  def compute(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).count do |pair|
      pair.first != pair.last
    end
  end
end
