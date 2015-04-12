class Hamming
  def compute(strand1, strand2)
    (0...[strand1.length, strand2.length].min).count { |i| strand1[i] != strand2[i] }
  end
end
