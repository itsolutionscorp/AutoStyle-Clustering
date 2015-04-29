class Hamming
  def self.compute(string_one, string_two)
    substring_distance(string_one, string_two)
  end

  def self.substring_distance(substring_one, substring_two)
    return 0 if substring_one.nil?
    character_distance(substring_one[0], substring_two[0]) +
      substring_distance(substring_one[1..-1], substring_two[1..-1])
  end

  def self.character_distance(character_one, character_two)
    character_one == character_two ? 0 : 1
  end
end
