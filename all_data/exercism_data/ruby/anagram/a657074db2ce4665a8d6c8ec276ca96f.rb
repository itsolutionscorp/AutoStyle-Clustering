class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.select { |w| @word.anagram_of?(w) }
  end
end

class Word < String
  def anagram_of?(word)
    letters(self) == letters(word)
  end

  private

  def letters(word)
    word.chars.sort
  end
end
