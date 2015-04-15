class Hamming
  def self.compute(strand1, strand2)
    distance = 0

    strand1.chars.zip(strand2.chars).each do |char1, char2|
      distance = distance + 1 if char1 != char2 
    end

    return distance
  end
end
