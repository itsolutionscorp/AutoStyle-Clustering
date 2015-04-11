class Hamming
  def self.compute(a, b)
    distance = 0
    a.each_char.with_index do |char, index|
      break if index >= b.length
      distance += 1 if char != b[index]
    end

    distance
  end
end
