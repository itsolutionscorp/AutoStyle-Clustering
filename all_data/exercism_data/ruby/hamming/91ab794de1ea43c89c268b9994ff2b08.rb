class Hamming

  def self.compute(a, b)
    count_the_number_of_character_differences_in_two_string(a, b)
  end

  def self.count_the_number_of_character_differences_in_two_string(a, b)
    a.chars.first(b.length).zip(b.chars).map { |i| i[0] != i[1] ? 1 : 0 }.reduce(:+)
  end

end
