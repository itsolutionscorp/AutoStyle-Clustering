class Hamming

  def self.compute(a, b)
    calculate_hamming_difference_between_strands(a, b)
  end

  private

  def self.calculate_hamming_difference_between_strands(a, b)
    count_the_number_of_character_differences_in_two_strings(a, b)
  end

  def self.count_the_number_of_character_differences_in_two_strings(a, b)
    zip_strings(a, b).map { |i| i[0] != i[1] ? 1 : 0 }.reduce(:+)
  end

  def self.zip_strings(a, b)
    a.chars.first(b.length).zip(b.chars)
  end

end
