class Anagram < String

  def match array
    @anagram_characters = characters self
    array.select { |word| anagram_of? word}
  end

  def anagram_of? word
    characters(word) == @anagram_characters
  end

  def characters string
    string.split('').sort
  end

end
