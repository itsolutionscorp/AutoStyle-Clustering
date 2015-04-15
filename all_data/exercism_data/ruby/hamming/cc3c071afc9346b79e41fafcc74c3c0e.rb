class Hamming
  def self.compute(string_one, string_two)
    string_one_chars = string_one.chars
    string_two_chars = string_two.chars

    hamming_distance = 0
    string_one_chars.each_with_index do |char_one, i|
      hamming_distance += (char_one == string_two_chars[i]) ? 0 : 1
    end
    hamming_distance
  end
end
