class Hamming

  def self.compute(strand1, strand2)
    distance = 0

    size = [strand1.size, strand2.size].min

    strand1.chars.take(size).each_with_index { |char, index|
      distance += 1 if char != strand2[index]
    }

    distance
  end

end
