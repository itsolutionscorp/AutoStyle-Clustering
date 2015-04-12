class Hamming

  def compute(strand1, strand2)
    (0..strand1.length).count{|i| strand1[i] != strand2[i]}
  end

end
