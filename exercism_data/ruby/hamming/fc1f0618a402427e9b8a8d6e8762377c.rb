class Hamming

  def self.compute(strand1, strand2)
    hamming_distance = 0

    smallest_strand_length = [strand1.length, strand2.length].min

    smallest_strand_length.times do |i|
      if strand1[i] != strand2[i]
        hamming_distance += 1
      end
    end
    hamming_distance
  end
end
