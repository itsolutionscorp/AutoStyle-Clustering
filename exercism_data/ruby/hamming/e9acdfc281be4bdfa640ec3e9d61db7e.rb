class Hamming
  def self.compute(seq1, seq2)
    seq1.size.times.count { |i| seq1[i] != seq2[i] }
  end
end
