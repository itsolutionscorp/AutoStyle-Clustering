class Hamming

  def self.compute left, right
    distance = 0
    left.chars.each_with_index do |char, i|
      if right[i]
        distance += 1 if right[i] != char
      end
    end
    distance
  end

end
