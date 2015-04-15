class Anagram < String

	def match array
    array.select { |word| equivalent_to_string? word}
  end

  def equivalent_to_string? word
    character_count(word) == character_count(self)
  end

  def character_count string
    string.split('').each_with_object(Hash.new(0)) { |key, memo| memo[key] += 1 }
  end

end
