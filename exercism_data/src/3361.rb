class Hamming

  def compute(str1, str2)
    distance = 0

    str1.chars.each_with_index do |char, index|
      distance += 1 if char != str2[index] && index < str2.length
    end

    distance
  end
end
