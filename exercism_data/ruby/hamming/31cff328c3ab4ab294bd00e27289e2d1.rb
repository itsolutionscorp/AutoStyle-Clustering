class Hamming
  def self.compute(string_one, string_two)
    string_one_chars = string_one.chars
    string_two_chars = string_two.chars

    hamming_distance = 0
    string_one_chars.each_with_index do |char_one, i|
      hamming_distance += (char_one == string_two_chars[i]) ? 0 : 1
    end
    hamming_distance

    # substring_distance(string_one, string_two)
  end

  # def self.substring_distance(substring_one, substring_two)
  #   return 0 if substring_one.nil?
  #   character_distance(substring_one[0], substring_two[0]) +
  #     substring_distance(substring_one[1..-1], substring_two[1..-1])
  # end

  # def self.character_distance(character_one, character_two)
  #   character_one == character_two ? 0 : 1
  # end
end
