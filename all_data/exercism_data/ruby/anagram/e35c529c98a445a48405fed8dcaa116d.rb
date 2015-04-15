class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.select { |other_word| anagram?(other_word.downcase) }
  end

  private
  def chars
    @chars ||= word.chars.sort
  end

  def anagram?(other_word)
    matches?(other_word) && !same?(other_word)
  end

  def matches?(other_word)
    chars.eql? other_word.chars.sort
  end

  def same?(other_word)
    word.eql? other_word
  end
end
