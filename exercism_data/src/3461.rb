class Hamming
  def compute(strand_1, strand_2)
    chars_1, chars_2 = strand_1.chars, strand_2.chars
    distance = index = 0

    while chars_1[index] && chars_2[index]
      distance += 1 if chars_1[index] != chars_2[index]
      index += 1
    end

    distance
  end
end
