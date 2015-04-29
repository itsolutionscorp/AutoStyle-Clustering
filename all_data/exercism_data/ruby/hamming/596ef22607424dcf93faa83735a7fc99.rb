class Hamming
  def self.compute(strand1, strand2)
    raise ArgumentError, "The Hamming distance is only defined for sequences of equal length." if strand1.length != strand2.length
    return 0 if strand1 == strand2

    distance = 0

    strand1.chars.each_with_index do |letter, index|
      distance += 1 if strand1[index] != strand2[index]
    end

    distance
  end
end
