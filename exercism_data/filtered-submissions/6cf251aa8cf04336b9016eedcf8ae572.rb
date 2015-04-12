class Hamming

  def compute(string_a, string_b)
    # get the length of the shortest string
    min_length = [string_a.to_s.length, string_b.to_s.length].min

    hamming_distance = 0

    # loop through each character up to the last one of the shortest string
    (0..(min_length - 1)).each do |index|
      character_a = string_a[index].upcase
      character_b = string_b[index].upcase

      # sum one to the hamming distance if the characters are not equal
      hamming_distance += 1 if character_a != character_b
    end

    return hamming_distance
  end

end
