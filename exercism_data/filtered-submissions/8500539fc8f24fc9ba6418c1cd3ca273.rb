class Hamming
  def compute(strand1, strand2)
    [strand1.size, strand2.size].min.times.count { |i| strand1[i] != strand2[i] }
  end
end
