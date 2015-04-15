class Hamming
  def self.compute(strand1, strand2)
    [strand1.length, strand2.length].min.times.count { |i|
      strand1[i] != strand2[i]
    }
  end
end
