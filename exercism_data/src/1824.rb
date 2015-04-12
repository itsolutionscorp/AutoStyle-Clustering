class Hamming

  def compute(strand1, strand2)
    l = [ strand1.length, strand2.length ].min
    (0..l-1).map { |i| strand1[i] == strand2[i] ? 0 : 1 }.reduce(:+)
  end
end
