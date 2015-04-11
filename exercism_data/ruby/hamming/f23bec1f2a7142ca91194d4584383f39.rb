class Hamming
  def self.compute(strand1, strand2)
    strand1 = strand1.chars.to_a
    strand2 = strand2.chars.to_a
    distance = 0
    while strand1.length > 0
      char1 = strand1.shift
      char2 = strand2.shift
      distance += 1 if char1 != char2
    end
    return distance
  end
end
