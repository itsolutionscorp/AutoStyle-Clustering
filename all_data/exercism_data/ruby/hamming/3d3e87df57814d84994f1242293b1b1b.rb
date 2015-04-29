class Hamming

  def self.compute(strand1, strand2)
    length = min_length(strand1, strand2)
    hamming_distance(strand1, strand2, length)
  end

  class << self
    def min_length(strand1, strand2)
      [strand1.length, strand2.length].min
    end

    def hamming_distance(strand1, strand2, length)
      length.times.count { |i| strand1[i] != strand2[i] }
    end
  end
end
