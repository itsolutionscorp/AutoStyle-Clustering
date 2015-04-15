class Hamming
  def self.compute(strand_A, strand_B)
    strand_A.length.times.count { |i| strand_A[i] != strand_B[i] }
  end
end
