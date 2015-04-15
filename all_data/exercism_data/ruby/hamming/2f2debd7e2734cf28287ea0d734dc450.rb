module Hamming
  def self.compute(strand_a, strand_b)
    res = 0
    stand_b = strand_b.chars
    strand_a.chars.each_with_index do |char, i|
      res += 1 unless char == strand_b[i] || strand_b[i].nil?
    end
    res
  end
end
