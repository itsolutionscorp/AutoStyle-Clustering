class Hamming
  def self.compute(strand_a, strand_b)
    strand_a.split('').map.with_index { |e, i| e == strand_b[i]? 0 : 1  }.reduce(:+)
  end
end
