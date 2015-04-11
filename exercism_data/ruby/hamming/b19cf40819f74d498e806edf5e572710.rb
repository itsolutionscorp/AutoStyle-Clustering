class Hamming
  def self.compute(first_string, second_string)
    hamming_distance = 0
    first_string.chars.each_with_index do |value, index|
      hamming_distance += 1 if value != second_string[index]
    end
    hamming_distance
  end
end
