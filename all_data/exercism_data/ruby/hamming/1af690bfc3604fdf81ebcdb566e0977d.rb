class Hamming
  
  def self.compute(strand1, strand2)
    min_strand_length = (0..[strand1, strand2].min.length - 1)
    min_strand_length.count { |i| strand1[i] != strand2[i] }
  end
  
end
