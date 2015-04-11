class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.select do |other_word|
      matches?(other_word) && !same?(other_word)
    end
  end

  private
  def chars
    @chars ||= word.chars.sort
  end

  def matches?(other_word)
    chars.eql? other_word.downcase.chars.sort
  end

  def same?(other_word)
    word.eql? other_word.downcase
  end
end
