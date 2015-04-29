class Hamming
  def self.compute(a,b)
    distance = 0
    a.chars.each_with_index.map { |char, index|
      distance = distance + 1 unless char == b[index]
    }
    distance
  end

end
