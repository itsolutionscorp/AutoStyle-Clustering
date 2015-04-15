class Hamming
  def self.compute(string_a, string_b)
    number_of_differences(string_a, string_b)
  end

  private

  def self.number_of_differences(string_a, string_b)
    character_differences(string_a, string_b).size
  end

  def self.character_differences(string_a, string_b)
    string_a.chars.zip(string_b.chars).select do |a, b|
      character_difference?(a, b)
    end
  end

  def self.character_difference?(a, b)
    a && b && a != b
  end
end
