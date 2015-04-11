class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(other_words)
    other_words.select { |w| anagram? w }
  end

  def anagram?(other_word)
    other = other_word.downcase
    other != word && word.chars.sort == other.chars.sort
  end
end
