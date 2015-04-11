class Hamming
  def self.compute(seq1, seq2)
    [seq1.size, seq2.size].min.times.count { |i| seq1[i] != seq2[i] }
  end
end
