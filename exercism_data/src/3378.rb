class Hamming

  def compute(strand_a, strand_b)
    min_length =  [strand_a.length, strand_b.length].min
    (0...min_length).count {|i| strand_a[i] != strand_b[i]}
  end
end
