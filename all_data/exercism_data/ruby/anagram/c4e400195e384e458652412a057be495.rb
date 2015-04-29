class Anagram

  def initialize(word)
    @word = word
  end

  def match(words = [])
    words.select do |candidate|
      anagram_of?(candidate) && different_from?(candidate)
    end
  end

  private

  def different_from?(other)
    word.downcase != other.downcase
  end

  def sort_chars_for(word)
    word.downcase.chars.sort
  end

  def anagram_of?(other)
    sort_chars_for(word) == sort_chars_for(other)
  end

  attr_reader :word

end
