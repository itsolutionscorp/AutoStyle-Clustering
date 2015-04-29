class Hamming
  def self.compute(original, other)
    distance = 0

    shortest_length = [original.length, other.length].min
    0.upto(shortest_length - 1).each do |index|
      distance += 1 unless original[index] == other[index]
    end

    distance
  end
end
