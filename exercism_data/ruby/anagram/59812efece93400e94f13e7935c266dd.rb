class Anagram < String

	def match array
    array.select { |word| anagram_of? word}
  end

  def anagram_of? word
    character_histogram(word) == character_histogram(self)
  end

  def character_histogram string
    string.split('').each_with_object(Hash.new(0)) { |character, histogram| histogram[character] += 1 }
  end

end
