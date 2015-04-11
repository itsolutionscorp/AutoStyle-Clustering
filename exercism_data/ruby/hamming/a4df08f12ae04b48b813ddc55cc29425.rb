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
      length.times.inject(0) do |distance, i|
        (strand1[i] != strand2[i]) ? distance += 1 : distance
      end
    end
  end
end
