class Hamming
  def self.compute(strand_a, strand_b)
    a, b = strand_a.chars, strand_b.chars
    hamming_distance = 0

    # FIXME : calc hamming distance without conditions
    a.each_with_index { |v, i| hamming_distance += 1 unless v.eql?(b[i]) }

    count
  end
end
