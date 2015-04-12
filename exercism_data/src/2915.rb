class Hamming
  def compute(str_1, str_2)
    differences = 0
    str_1.chars.each_with_index do |char, index|
      differences += 1 if str_2[index] != char
    end
    differences
  end
end
