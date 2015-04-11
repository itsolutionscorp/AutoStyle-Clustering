class Hamming

  def self.compute left, right
    distance = 0
    left.chars.each_with_index do |char, i|
      distance += 1 if right[i] and right[i] != char
    end
    distance
  end

end
