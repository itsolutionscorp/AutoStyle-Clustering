class Hamming

  def self.compute( first_string, second_string )
    hamming_distance = 0
    first_string.each_char.with_index do |char, index|
      if second_string[index] != nil
        hamming_distance += 1 unless char == second_string[index]
      end
    end
    return hamming_distance
  end

end
