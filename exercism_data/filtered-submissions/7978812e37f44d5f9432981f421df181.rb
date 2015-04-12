class Hamming
  def compute(a, b)
    hamming_difference = 0
    character_index = 0
    a.each_char do |a_char|
      hamming_difference += 1 if a_char != b[character_index]
      character_index += 1
    end

    hamming_difference
  end
end
