class Hamming
  def self.compute(a, b)
    distance = 0
    a.chars.each_with_index do |symbol, index|
      distance += 1 if b[index] != symbol
    end
    return distance
  end
end
