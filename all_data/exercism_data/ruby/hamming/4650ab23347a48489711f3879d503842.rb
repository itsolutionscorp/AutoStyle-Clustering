class Hamming

  def self.compute(str_1, str_2)
    distance = 0
    str_1.chars.each_with_index do |char, index|
      distance += 1 if str_2[index] != char
    end
    distance
  end
end
