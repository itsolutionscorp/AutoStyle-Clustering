class Anagram < String

  attr_accessor :anagram_characters

  def match words
    words.select { |word| anagram_of? word }
  end

  def anagram_of? word
    alphabetize(word) == @anagram_characters
  end

  def anagram_characters
    @anagram_characters ||= alphabetize self
  end

  def alphabetize word
    word.chars.sort
  end

end
