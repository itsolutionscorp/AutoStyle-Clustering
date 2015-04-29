class Anagram < String

	def match array
    result = array.select { |word| equivalent_anagrams? self, word}
  end

  def equivalent_anagrams? string1, string2
    character_count(string1) == character_count(string2)
  end

  def character_count string
    string.split('').each_with_object(Hash.new(0)) { |key, memo| memo[key] += 1 }
  end

end
