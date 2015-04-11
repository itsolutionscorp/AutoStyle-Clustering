class Hamming
  def self.compute(strand1, strand2)
    distance = 0
    minimum_length(strand1, strand2).times do |i|
      if strand1[i] != strand2[i]
        distance += 1
      end
    end
    distance
  end

  def self.minimum_length(strand1, strand2)
    [strand1.length, strand2.length].min
  end
end
