class Hamming
  def self.compute(strand1, strand2)
    minimum_length(strand1, strand2).times.count { |i|
      strand1[i] != strand2[i]
    }
  end

  def self.minimum_length(strand1, strand2)
    [strand1.length, strand2.length].min
  end
end
