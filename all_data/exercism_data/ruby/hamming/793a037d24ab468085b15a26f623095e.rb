class Hamming

  def self.compute(strand1, strand2)
    return 0 if strand1 == strand2
    length = [ strand1.length, strand2.length ].min
    (0...length).count { |i| strand1[i] != strand2[i] }
  end
end
